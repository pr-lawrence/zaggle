package models.authentication

import play.api.libs.json.Json

/**
  *
  * @author Lawrence
  * @since 2018. 2. 21.
  * @note
  * @version 0.1.1
  */
case class AuthToken (accessToken: String, refreshToken: String)

object AuthToken{
  implicit val format = Json.format[AuthToken]
}