package services

import javax.inject._

import common.JwtUtils
import models.authentication.{AuthToken, LoginRequest, UserRepository}
import play.api.libs.json.{JsNumber, JsObject, JsString}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
  *
  * @author Lawrence
  * @since 2018. 2. 21.
  * @note
  * @version 0.1.1
  */
@Singleton
class AuthenticationService @Inject()(userRepository: UserRepository,
                                      loginService: LoginService) {

  def localLogin(loginRequest: LoginRequest): Future[Option[AuthToken]] = {
    loginService.login(loginRequest).map(userOpt =>
      userOpt match {
        case Some(user) =>
          val jsObject = JsObject(Seq("userId" -> JsNumber(user.userId),
            "loginId" -> JsString(user.loginId)))

          val accessToken = JwtUtils.tokenOf(jsObject)
          val refreshToken = ""

          Option(AuthToken(accessToken, refreshToken))
        case _ =>
          None
      }
    )
  }
}
