package services

import javax.inject._

import common.JwtUtils
import models.authentication._
import models.common.ZaggleRequestContext

import play.api.Logger
import play.api.libs.json._
import play.api.libs.ws._

import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global


/**
  *
  * @author Lawrence
  * @since 2018. 2. 21.
  * @note
  * @version 0.1.1
  */
@Singleton
class AuthenticationService @Inject()(ws: WSClient,
                                      userRepository: UserRepository,
                                      loginService: LoginService) {

  def localLogin(loginRequest: LoginRequest): Future[Option[AuthToken]] = {
    loginService.login(loginRequest).map(userOpt =>
      userOpt match {
        case Some(user) =>
          val jsObject = Json.toJson(ZaggleRequestContext(user.userId, user.loginId)).as[JsObject]
          val accessToken = JwtUtils.tokenOf(jsObject)
          val refreshToken = ""

          Option(AuthToken(accessToken, refreshToken))
        case _ =>
          None
      }
    )
  }

  def githubLogin(githubLoginRequest: GithubLoginRequest): Future[Option[AuthToken]] = {
    val url: String = "https://github.com/login/oauth/access_token"
    val response = ws.url(url)
      .post(Json.toJson(GithubOauthRequest("8357719d0e98809c331e", "", githubLoginRequest.code)))
      .flatMap { response =>
        val token = response.body.split("&").map(_.split("=")).filter(_ (0) == "access_token").head(1)

        ws.url("https://api.github.com/user")
          .withHttpHeaders("Authorization" -> s"token ${token}")
          .get()
          .map { response =>
            response.body
          }
      }

    response.map { body =>
      Logger.info(s"${body}")
      val jsObject = Json.parse(body).as[JsObject]
      val accessToken = JwtUtils.tokenOf(jsObject)
      val refreshToken = ""

      Option(AuthToken(accessToken, refreshToken))
    }
  }
}
