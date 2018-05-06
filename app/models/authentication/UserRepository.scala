package models.authentication

import java.sql.Date
import java.time.{LocalDateTime, LocalTime}
import javax.inject.{Inject, Singleton}

import play.api.db.slick._
import slick.jdbc.JdbcProfile

import scala.concurrent._

/**
  *
  * @author Lawrence
  * @since 2018. 2. 22.
  * @note
  * @version 0.1.1
  */
@Singleton
class UserRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  // We want the JdbcProfile for this provider
  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  private implicit val localDateToDate = MappedColumnType.base[LocalDateTime, Date](
    ldt => Date.valueOf(ldt.toLocalDate()),
    d => LocalDateTime.of(d.toLocalDate, LocalTime.MIDNIGHT)
  )

  private class UserTable(tag: Tag) extends Table[User](tag, "tb_user") {

    def userId = column[Option[Long]]("user_id", O.PrimaryKey, O.AutoInc)

    def loginId = column[String]("login_id")

    def password = column[String]("password")

    def refreshToken = column[Option[String]]("refresh_token")

    def provider = column[Option[String]]("provider")

    def regiDate = column[Option[LocalDateTime]]("regi_date")

    def editDate = column[Option[LocalDateTime]]("edit_date")

    /**
      * This is the tables default "projection".
      *
      * It defines how the columns are converted to and from the Person object.
      *
      * In this case, we are simply passing the id, name and page parameters to the Person case classes
      * apply and unapply methods.
      */

    def * = (userId, loginId, password, refreshToken, provider, regiDate, editDate) <> ((User.apply _).tupled, User.unapply)
  }

  private val user = TableQuery[UserTable]

  def select(id: Option[Long]): Future[Option[User]] = db.run {
    user.filter(_.userId === id)
      .result
      .headOption
  }

  def selectByLogin(loginId: String, password: String): Future[Option[User]] = db.run {
    user.filter(_.loginId === loginId)
      .filter(_.password === password)
      .result
      .headOption
  }

  def selectByProvider(loginId: String, provider: String): Future[Option[User]] = db.run {
    user.filter(_.loginId === loginId)
      .filter(_.provider === provider)
      .result
      .headOption
  }

  def insert(param: User): Future[User] = db.run {
    (user.map(e => (e.loginId, e.password, e.refreshToken, e.provider, e.regiDate, e.editDate))
      returning user.map(_.userId)
      into ((column, id) => User(id, column._1, column._2, column._3, column._4, column._5, column._6))
      ) += (param.loginId, param.password, param.refreshToken, param.provider, Some(LocalDateTime.now), param.editDate)
  }

  def update(param: User): Future[Int] = db.run {
    user.filter(_.userId === param.userId)
      .map(e => (e.refreshToken))
      .update((param.refreshToken))
  }

  def updateRefreshToken(userId: Long, refreshToken: String): Future[Int] = db.run {
    user.filter(_.userId === userId)
      .map(e => (e.refreshToken))
      .update((Option(refreshToken)))
  }

  /*
    def update(param: Discussion): Future[Int] = db.run {
      discussion.filter(_.discusId === param.discusId)
        .map(e => (e.competId, e.title, e.content, e.author, e.subject, e.editDate))
        .update((param.competId, param.title, param.content, param.author, param.subject, Some(LocalDateTime.now)))
    }
  */
}
