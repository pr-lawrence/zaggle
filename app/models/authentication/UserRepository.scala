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

  private class UserTable(tag: Tag) extends Table[User](tag, "user") {

    implicit val localDateToDate = MappedColumnType.base[LocalDateTime, Date](
      ldt => Date.valueOf(ldt.toLocalDate()),
      d => LocalDateTime.of(d.toLocalDate, LocalTime.MIDNIGHT)
    )

    def userId = column[Long]("user_id", O.PrimaryKey, O.AutoInc)

    def loginId = column[String]("login_id")

    def password = column[String]("password")

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

    def * = (userId, loginId, password, regiDate, editDate) <> ((User.apply _).tupled, User.unapply)
  }

  private val user = TableQuery[UserTable]

  def get(id: Long): Future[Seq[User]] = db.run {
    user.filter(_.userId === id).result
  }

  def getByLogin(loginId: String, password: String): Future[Option[User]] = db.run {
    user.filter(x => x.loginId === loginId && x.password === password).result.headOption
  }

}
