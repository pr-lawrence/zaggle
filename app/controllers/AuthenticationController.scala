package controllers

import javax.inject.{Inject, Singleton}

import models.authentication._
import play.api.libs.json._
import play.api.mvc.{AbstractController, ControllerComponents, Request}
import services.AuthenticationService

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
class AuthenticationController @Inject()(cc: ControllerComponents, authenticationService : AuthenticationService) extends AbstractController(cc) {

  /**
    * basic login
    * @return
    */
  def local = Action.async(parse.json) { request: Request[JsValue] =>
    request.body.asOpt[LoginRequest] match {
      case Some(loginRequest) =>
        authenticationService.localLogin(loginRequest).map( authOpt =>
          if( authOpt.isDefined) Ok(Json.toJson(authOpt.get))
          else BadRequest("")
        )
      case _ =>
        Future(BadRequest(""))
    }
  }

}
