package services

import javax.inject._

import com.typesafe.config.ConfigFactory
import common.JwtUtils
import models.authentication._
import models.common.ZaggleRequestContext
import play.api.libs.json._
import play.api.libs.ws._

import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global
import java.util.UUID.randomUUID

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
  val GITHUB_CLIENT_SECRET = config.getString("social.github.client-secret")

  /**
    * local Login
    *
    * @param loginRequest
    * @return
    */
  def localLogin(loginRequest: LoginRequest): Future[Option[AuthToken]] = {
    loginService.login(loginRequest).map(userOpt =>
      userOpt match {
        case Some(user) =>
          val jsObject = Json.toJson(ZaggleRequestContext(user.userId, user.loginId, "")).as[JsObject]
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
    *
    * @param githubLoginRequest
    * @return
    */
  def githubLogin(githubLoginRequest: GithubLoginRequest): Future[Option[AuthToken]] = {
    def getAccessToken(): Future[Option[String]] = {
      ws.url("https://github.com/login/oauth/access_token")
        .post(Json.toJson(GithubOauthRequest(GITHUB_CLIENT_ID, GITHUB_CLIENT_SECRET, githubLoginRequest.code)))
        .map { response =>
          if (response.status != 200) None
          else if (response.body.contains("error")) None
          else Some(response.body.split("&").map(_.split("=")).filter(_ (0) == "access_token").head(1))
        }
    }

    def getUser(tokenOpt: Option[String]): Future[Option[GithubUser]] = {
      tokenOpt match {
        case Some(token) =>
          ws.url("https://api.github.com/user")
            .withHttpHeaders("Authorization" -> s"token ${token}")
            .get()
            .map { response =>
              Some(Json.parse(response.body).as[GithubUser])
            }
        case _ =>
          Future(None)
      }
    }

    val result = for {
      accessTokenOpt <- getAccessToken()
      userOpt <- getUser(accessTokenOpt)
    } yield {
      userOpt match {
        case Some(githubUser) =>
          val loginId = githubUser.login
          val userFuture: Future[User] = userRepository.selectByProvider(loginId, "github").flatMap { userOpt =>
            userOpt match {
              case Some(user) =>
                Future(user)
              case _ =>
                val user = User(None, loginId, "", None, Some("github"), None, None)
                userRepository.insert(user)
            }
          }
          userFuture.map { user =>
            val accessToken = createAccessToken(ZaggleRequestContext(user.userId, githubUser.login, githubUser.avatarUrl))
            val uuid = randomUUID.toString
            userRepository.updateRefreshToken(githubUser.id, uuid)
            val refreshToken = JwtUtils.tokenOf(Map("refreshToken" -> uuid))
            Some(AuthToken(accessToken, refreshToken))
          }
        case _ =>
          Future(None)
      }
    }
    result.flatMap(x => x)
  }

  /**
    * refresh Token
    *
    * @param zaggleRequestContext
    * @return
    */
  def refreshToken(zaggleRequestContext: ZaggleRequestContext): AuthToken = {
    val accessToken = createAccessToken(zaggleRequestContext)
    val refreshToken = createRefreshTokenNSave(zaggleRequestContext.userId.get)
    AuthToken(accessToken, refreshToken)
  }

  private def createAccessToken(zaggleRequestContext: ZaggleRequestContext): String = {
    val jsObject = Json.toJson(zaggleRequestContext).as[JsObject]
    JwtUtils.tokenOf(jsObject)
  }

  private def createRefreshTokenNSave(userId: Long): String = {
    val uuid = randomUUID.toString
    userRepository.updateRefreshToken(userId, uuid)
    JwtUtils.tokenOf(Map("refreshToken" -> uuid))
  }

}
