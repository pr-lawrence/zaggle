package controllers

import javax.inject.{Inject, Singleton}

import common.AuthorizedAction
import models.authentication._
import play.api.libs.json._
import play.api.mvc.{AbstractController, ControllerComponents, Request}
import services.{AuthenticationService, UserService}

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
class AuthenticationController @Inject()(cc: ControllerComponents
                                         , authorizedAction: AuthorizedAction
                                         , userService: UserService
                                         , authenticationService: AuthenticationService) extends AbstractController(cc) {

  /**
    * basic login
    *
    * @return
    */
  def local = Action.async(parse.json) { request: Request[JsValue] =>
    request.body.asOpt[LoginRequest] match {
      case Some(loginRequest) =>
        authenticationService.localLogin(loginRequest).map(authOpt =>
          if (authOpt.isDefined) Ok(Json.toJson(authOpt.get))
          else BadRequest("")
        )
      case _ =>
        Future(BadRequest(""))
    }
  }

  /**
    * login using Github
    *
    * @return
    */
  def loginUsingGithub = Action.async(parse.json) { request: Request[JsValue] =>
    request.body.asOpt[GithubLoginRequest] match {
      case Some(request) =>
        authenticationService.githubLogin(request).map { authOpt =>
          if (authOpt.isDefined) Ok(Json.toJson(authOpt.get))
          else BadRequest("")
        }
      case _ =>
        Future(BadRequest(""))
    }
  }

  /**
    * refresh Token
    *
    * @return
    */
  def refreshToken = authorizedAction.async(parse.json) { implicit request =>
    val reqContextOpt = request.attrs.get(AuthorizedAction.JWT_KEY)
    request.body \ "refreshToken" match {
      case JsDefined(refreshToken) =>
        // refreshToken이 맞는지 확인
        val userId = reqContextOpt.flatMap(_.userId)
        userService.get(userId).map( userOpt =>
          if(userOpt.flatMap(_.refreshToken).filter(_ == refreshToken.toString).size == 0) {
            Ok(Json.toJson(authenticationService.refreshToken(reqContextOpt.get)))
          } else {
            Unauthorized("refreshToken is invalid")
          }
        )
      case _ =>
        Future(BadRequest("Request is invalid"))
    }
  }
}
