package models.authentication

import play.api.libs.json.JsonNaming._
import play.api.libs.json._

/**
  *
  * @author Lawrence
  * @since 2018. 3. 1.
  * @note
  * @version 0.1.1
  */
case class GithubOauthRequest (clientId: String, clientSecret: String, code: String)

object GithubOauthRequest {
  implicit val config = JsonConfiguration(SnakeCase)
  implicit val format = Json.format[GithubOauthRequest]
}