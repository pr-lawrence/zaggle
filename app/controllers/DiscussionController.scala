package controllers

import javax.inject._

import common.AuthorizedAction
import io.swagger.annotations.{Api, ApiOperation}
import models.competition.Competition
import models.discussion.Discussion
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
  * @version 0.2.0
  */
@Api(value = "Discussion")
@Singleton
class DiscussionController @Inject()(cc: ControllerComponents
                                     , authorizedAction: AuthorizedAction
                                     , discussionService: DiscussionService) extends AbstractController(cc) {

  @ApiOperation(value = "List of Discussion",
    notes = "Discussion 리스트 정보를 반환한다.",
    response = classOf[Discussion],
    responseContainer = "List")
  def list = Action.async { implicit request =>
    discussionService.list().map { discussions =>
      Ok(Json.toJson(discussions))
    }
  }

  @ApiOperation(value = "Discussion",
    notes = "Discussion을 반환한다.",
    response = classOf[Discussion])
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

  @ApiOperation(value = "Create Discussion",
    notes = "Discussion을 생성한다",
    response = classOf[Discussion])
  def create() = authorizedAction.async(parse.json) { request: Request[JsValue] =>
    println(request.body)
    request.body.asOpt[Discussion] match {
      case Some(discussion) =>
        discussionService.create(discussion).map { discussion =>
          Ok(Json.toJson(discussion))
        }
      case _ =>
        Future(BadRequest(""))
    }
  }

  @ApiOperation(value = "List of Competition",
    notes = "Competition 리스트 정보를 반환한다.",
    response = classOf[Competition],
    responseContainer = "List")
  def modity() = TODO

  //  def modify() = Action.async { implicit request =>
  //    Future {
  //      NotFound
  //    }
  //  }
}