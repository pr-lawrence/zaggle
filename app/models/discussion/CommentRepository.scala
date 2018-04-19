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
  * @since 2018. 4. 20.
  * @note
  * @version 0.0.2
  */
@Singleton
class CommentRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
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


  private class CommentTable(tag: Tag) extends Table[Comment](tag, "tb_comment") {
    def commentId = column[Long]("comment_id", O.PrimaryKey, O.AutoInc)

    def relationId = column[Long]("relation_id")

    def commentType = column[String]("comment_type")

    def comment = column[String]("comment")

    def author = column[String]("author")

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

    def * = (commentId, relationId, commentType, comment, author, regiDate, editDate) <> ((Comment.apply _).tupled, Comment.unapply)
  }

  /**
    * The starting point for all queries on the people table.
    */
  private val comment = TableQuery[CommentTable]

  def select(): Future[Seq[Comment]] = db.run {
    comment.result
  }

  def selectById(id: Long): Future[Option[Comment]] = db.run {
    comment.filter(_.commentId === id).result.headOption
  }

  def selectBytypeNRelationId(commentType: String, relationId: Long): Future[Seq[Comment]] = db.run {
    comment.filter(_.commentType === commentType)
      .filter(_.relationId === relationId).result
  }

  def insert(param: Comment) = db.run {
    (comment.map(e => (e.relationId, e.commentType, e.comment, e.author, e.regiDate, e.editDate))
      returning comment.map(_.commentId)
      into ((column, id) => Comment(id, column._1, column._2, column._3, column._4, column._5, column._6))
      ) += (param.relationId, param.commentType, param.comment, param.author, param.regiDate, param.editDate)
  }

  def update(dParam: Comment): Future[Seq[Comment]] = db.run {
    comment.result
  }

}
