package models.authentication

import play.api.libs.json._

/**
  *
  * @author Lawrence
  * @since 2018. 2. 21.
  * @note
  * @version 0.1.1
  */
case class LoginRequest(id: String, password: String)

object LoginRequest {
  implicit val format = Json.format[LoginRequest]
}