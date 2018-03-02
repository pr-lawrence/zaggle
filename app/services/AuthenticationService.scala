package services

import javax.inject._

import com.typesafe.config.ConfigFactory
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

  val config = ConfigFactory.load()

  val GITHUB_CLIENT_ID = config.getString("social.github.client-id")
  val GITHUB_CLIENT_SECRET =  config.getString("social.github.client-secret")

  /**
    * local Login
    * @param loginRequest
    * @return
    */
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

  /**
    * Login using Github
    * @param githubLoginRequest
    * @return
    */
  def githubLogin(githubLoginRequest: GithubLoginRequest): Future[Option[AuthToken]] = {
    def getAccessToken(): Future[Option[String]] = {
      ws.url("https://github.com/login/oauth/access_token")
        .post(Json.toJson(GithubOauthRequest(GITHUB_CLIENT_ID, GITHUB_CLIENT_SECRET, githubLoginRequest.code)))
        .map { response =>
          if( response.status != 200 ) None
          else Some(response.body.split("&").map(_.split("=")).filter(_ (0) == "access_token").head(1))
        }
    }

    def getUser(tokenOpt: Option[String]): Future[Option[String]] = {
      tokenOpt match {
        case Some(token) =>
          ws.url("https://api.github.com/user")
            .withHttpHeaders("Authorization" -> s"token ${token}")
            .get()
            .map { response =>
              Some(response.body)
            }
        case _ =>
          Future(None)
      }
    }

    for {
      accessTokenOpt <- getAccessToken()
      userOpt <- getUser(accessTokenOpt)
    } yield {
      userOpt match {
        case Some(user) =>
          val jsObject = Json.parse(user).as[JsObject]
          val accessToken = JwtUtils.tokenOf(jsObject)
          val refreshToken = ""

          Some(AuthToken(accessToken, refreshToken))
        case _ =>
          None
      }
    }
  }

}
