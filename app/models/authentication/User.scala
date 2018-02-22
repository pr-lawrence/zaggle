package models.authentication

import java.time.LocalDateTime

import play.api.libs.json.Json

/**
  *
  * @author Lawrence
  * @since 2018. 2. 22.
  * @note
  * @version
  */
case class User (userId: Long, loginId: String, password: String, regiDate: LocalDateTime, editDate: Option[LocalDateTime])

object User{
  implicit val format = Json.format[User]
}