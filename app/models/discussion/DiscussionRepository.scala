package models.discussion

import java.sql.Timestamp
import java.time.{LocalDateTime, ZoneOffset}
import javax.inject.{Inject, Singleton}

import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

/**
  *
  * @userId Lawrence
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

    def discusId = column[Option[Long]]("discus_id", O.PrimaryKey, O.AutoInc)

    def competId = column[Long]("compet_id")

    def title = column[String]("title")

    def content = column[String]("content")

    def userId = column[Option[Long]]("user_id")

//    def subject = column[String]("subject")

    def delFlag = column[Option[Boolean]]("del_flag")

    def regiDate = column[Option[LocalDateTime]]("regi_date")

    def editDate = column[Option[LocalDateTime]]("edit_date")

    def delDate = column[Option[LocalDateTime]]("del_date")

    /**
      * This is the tables default "projection".
      *
      * It defines how the columns are converted to and from the Person object.
      *
      * In this case, we are simply passing the id, name and page parameters to the Person case classes
      * apply and unapply methods.
      */

    def * = (discusId, competId, title, content, userId, delFlag, regiDate, editDate, delDate) <> ((Discussion.apply _).tupled, Discussion.unapply)
  }

  /**
    * The starting point for all queries on the people table.
    */
  private val discussion = TableQuery[DiscussionTable]

  def select(): Future[Seq[Discussion]] = db.run {
    discussion.result
  }

  def selectByCompetitionId(competitionId: Long): Future[Seq[Discussion]] = db.run {
    discussion.filter(_.competId === competitionId)
      .filter(_.delFlag === false)
      .result
  }

  def selectById(id: Long): Future[Option[Discussion]] = db.run {
    discussion.filter(_.discusId === id).result.headOption
  }

  def insert(param: Discussion): Future[Discussion] = db.run {
    (discussion.map(e => (e.competId, e.title, e.content, e.userId, e.delFlag, e.regiDate, e.editDate, e.delDate))
      returning discussion.map(_.discusId)
      into ((column, id) => Discussion(id, column._1, column._2, column._3, column._4, column._5, column._6, column._7, column._8))
      ) += (param.competId, param.title, param.content, param.userId, Some(false), Some(LocalDateTime.now), param.editDate, param.delDate)
  }

  def update(param: Discussion): Future[Int] = db.run {
    discussion.filter(_.discusId === param.discusId)
      .map(e => (e.competId, e.title, e.content, e.userId, e.editDate))
      .update((param.competId, param.title, param.content, param.userId, Some(LocalDateTime.now)))
  }

  def delete(id: Long): Future[Int] = db.run {
    discussion.filter(_.discusId === id)
      .map(e => (e.delFlag, e.delDate))
      .update((Some(true), Some(LocalDateTime.now)))
  }
}