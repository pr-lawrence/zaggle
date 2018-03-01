package models.authentication

import play.api.libs.json._

/**
  *
  * @author Lawrence
  * @since 2018. 3. 1.
  * @note
  * @version 0.1.1
  */
case class GithubLoginRequest (code: String)

object GithubLoginRequest{
  implicit val format = Json.format[GithubLoginRequest]
}