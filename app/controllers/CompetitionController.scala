package controllers

import javax.inject._

import io.swagger.annotations.{Api, ApiOperation}
import models.competition.{Competition, CompetitionRepository}
import play.api.libs.json.Json
import play.api.mvc._
import services.competition.CompetitionService

import scala.concurrent.{ExecutionContext, Future}


/**
  *
  * @author: Lawrence
  * @since: 2018. 2. 4.
  * @note: Competition Controller
  * @version: 0.1.0
  */
@Api(value = "Competition")
@Singleton
class CompetitionController @Inject()(cc: ControllerComponents, competitionService: CompetitionService, competitionRepository: CompetitionRepository)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  @ApiOperation(value = "Finds Pets by status",
    notes = "Multiple status values can be provided with comma seperated strings",
    response = classOf[Competition],
    responseContainer = "List")
  def list() = Action.async { implicit request =>
    //    val competitions = competitionService.list()
    val competitions = competitionRepository.list()

    competitions.map { comps =>
      Ok(Json.toJson(comps))
    }
  }

  @ApiOperation(value = "Finds Pets by status",
    notes = "Multiple status values can be provided with comma seperated strings",
    response = classOf[Competition],
    responseContainer = "List")
  def get(id: String) = Action { implicit request =>
    val maybeCompetition = competitionService.get(id)
    maybeCompetition match {
      case Some(competition) =>
        Ok(Json.toJson(competition))
      case _ =>
        NotFound
    }
  }
}
