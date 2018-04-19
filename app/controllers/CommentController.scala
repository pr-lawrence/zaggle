package controllers

import javax.inject.{Inject, Singleton}

import io.swagger.annotations.{Api, ApiOperation}
import models.competition.Competition
import models.discussion.Comment
import play.api.Logger
import play.api.libs.json.Json
import play.api.mvc._
import services.CommentService

import scala.concurrent._
/**
  *
  * @author Lawrence
  * @since 2018. 4. 20.
  * @note
  * @version 0.2.0
  */
@Api(value = "Comment")
@Singleton
class CommentController @Inject()(cc: ControllerComponents,
                                  commentService: CommentService)
                                 (implicit ec: ExecutionContext) extends AbstractController(cc) {


  @ApiOperation(value = "List of Comment",
    notes = "Comment 리스트 정보를 반환한다.",
    response = classOf[Comment],
    responseContainer = "List")
  def list(commentType: String, id: Long) = Action.async { implicit request =>
    commentService.getByTypeNRelationId(commentType, id).map { competitions =>
      Ok(Json.toJson(competitions))
    }
  }
}
