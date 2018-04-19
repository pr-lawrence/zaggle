package models.discussion

import java.sql.Timestamp
import java.time.{LocalDateTime, ZoneOffset}
import javax.inject.{Inject, Singleton}

import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

/**
  *
  * @author Lawrence
  * @since 2018. 4. 5.
  * @note
  * @version
  */
@Singleton
class DiscussionRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {

  // We want the JdbcProfile for this provider
  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  // These imports are important, the first one brings db into scope, which will let you do the actual db operations.
  // The second one brings the Slick DSL into scope, which lets you define the table and other queries.
  import dbConfig._
  import profile.api._

  private implicit val localDateTimeColumnType = MappedColumnType.base[LocalDateTime, Timestamp](
    ldt => Timestamp.from(ldt.toInstant(ZoneOffset.ofHours(9))),
    d => d.toLocalDateTime
  )

  private class DiscussionTable(tag: Tag) extends Table[Discussion](tag, "tb_discussion") {
    /** The ID column, which is the primary key, and auto incremented */
    //    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    /** The name column */
    //    def name = column[String]("name")

    /** The age column */
    //    def age = column[Int]("age")

    def discusId = column[Long]("discus_id", O.PrimaryKey, O.AutoInc)

    def competId = column[Long]("compet_id")

    def title = column[String]("title")

    def content = column[String]("content")

    def author = column[String]("author")

    def subject = column[String]("subject")

    def regiDate = column[LocalDateTime]("regi_date")

    def editDate = column[Option[LocalDateTime]]("edit_date")

    /**
      * This is the tables default "projection".
      *
      * It defines how the columns are converted to and from the Person object.
      *
      * In this case, we are simply passing the id, name and page parameters to the Person case classes
      * apply and unapply methods.
      */

    def * = (discusId, competId, title, content, author, subject, regiDate, editDate) <> ((Discussion.apply _).tupled, Discussion.unapply)
  }

  /**
    * The starting point for all queries on the people table.
    */
  private val discussion = TableQuery[DiscussionTable]

  def select(): Future[Seq[Discussion]] = db.run {
    discussion.result
  }

  def selectById(id: Long): Future[Option[Discussion]] = db.run {
    discussion.filter(_.discusId === id).result.headOption
  }

  def insert(dParam: Discussion) = db.run {
    (discussion.map(d => (d.competId, d.title, d.content, d.author, d.subject, d.regiDate, d.editDate))
      returning discussion.map(_.discusId)
      into ((dData, id) => Discussion(id, dData._1, dData._2, dData._3, dData._4, dData._5, dData._6, dData._7))
      ) += (dParam.competId, dParam.title, dParam.content, dParam.author, dParam.subject, dParam.regiDate, dParam.editDate)
  }

  def update(dParam: Discussion): Future[Seq[Discussion]] = db.run {
    discussion.result
  }

}
