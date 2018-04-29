package models.authentication

import play.api.libs.json._
import play.api.libs.json.JsonNaming.SnakeCase

/**
  *
  * @author Lawrence
  * @since 2018. 3. 3.
  * @note
  * @version 0.1.1
  */
case class GithubUser(
                       login: String,
                       id: Int,
                       avatarUrl: String,
                       //                          gravatarId: String,
                       //                          url: String,
                       //                          htmlUrl: String,
                       //                          followersUrl: String,
                       //                          followingUrl: String,
                       //                          gistsUrl: String,
                       //                          starredUrl: String,
                       //                          subscriptionsUrl: String,
                       //                          organizationsUrl: String,
                       //                          reposUrl: String,
                       //                          eventsUrl: String,
                       //                          receivedEventsUrl: String,
                       //                          `type`: String,
                       //                          siteAdmin: Boolean,
                       name: Option[String],
                       //                          company: String,
                       //                          blog: String,
                       //                          location: String,
                       email: Option[String],
                       //                          hireable: Boolean,
                       //                          bio: String,
                       //                          publicRepos: Int,
                       //                          publicGists: Int,
                       //                          followers: Int,
                       //                          following: Int,
                       //                          createdAt: String,
                       //                          updatedAt: String
                     )

object GithubUser {
  implicit val config = JsonConfiguration(SnakeCase)
  implicit val format = Json.format[GithubUser]
}