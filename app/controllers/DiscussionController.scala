package controllers

import javax.inject._

import play.api.libs.json._
import play.api.mvc._
import services.DiscussionService

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global


/**
  *
  * @author Lawrence
  * @since 2018. 4. 5.
  * @note
  * @version
  */

@Singleton
class DiscussionController @Inject()(cc: ControllerComponents, discussionService: DiscussionService) extends AbstractController(cc) {

  def list = Action.async { implicit request =>
    discussionService.list().map { discussions =>
      Ok(Json.toJson(discussions))
    }
  }

  def get(id: Long) = Action.async { implicit request =>
    discussionService.getById(id).map { discussion =>
      discussion match {
        case Some(discussion) =>
          Ok(Json.toJson(discussion))
        case _ =>
          NotFound
      }
    }
  }

  def create() = Action.async(parse.json) { request: Request[JsValue] =>
    Future {
      NotFound
    }
    //    request.body.asOpt[LoginRequest] match {
    //      case Some(loginRequest) =>
    //        authenticationService.localLogin(loginRequest).map( authOpt =>
    //          if( authOpt.isDefined) Ok(Json.toJson(authOpt.get))
    //          else BadRequest("")
    //        )
    //      case _ =>
    //        Future(BadRequest(""))
    //    }
  }

  def modify() = Action.async { implicit request =>
    Future {
      NotFound
    }
  }

}