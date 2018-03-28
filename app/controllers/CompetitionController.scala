package controllers

import javax.inject._

import io.swagger.annotations.{Api, ApiOperation}
import models.competition.{Competition, CompetitionOverviewRepository, CompetitionRepository}
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent._


/**
  *
  * @author: Lawrence
  * @since: 2018. 2. 4.
  * @note: Competition Controller
  * @version: 0.1.0
  */
@Api(value = "Competition")
@Singleton
class CompetitionController @Inject()(cc: ControllerComponents,
                                      competitionRepository: CompetitionRepository,
                                      competitionOverviewRepository: CompetitionOverviewRepository)
                                     (implicit ec: ExecutionContext) extends AbstractController(cc) {

  @ApiOperation(value = "List of Competition",
    notes = "Competition 리스트 정보를 반환한다.",
    response = classOf[Competition],
    responseContainer = "List")
  def list() = Action.async { implicit request =>
    //    val competitions = competitionService.list()
    competitionRepository.list().map { competitions =>
      Ok(Json.toJson(competitions))
    }
  }

  @ApiOperation(value = "Competition",
    notes = "Competition을 반환한다.",
    response = classOf[Competition])
  def get(id: Long) = Action.async { implicit request =>
    competitionRepository.get(id).map { competitions =>

      if(competitions.isEmpty) {
        NotFound
      } else {
        Ok(Json.toJson(competitions.head))
      }
    }
  }

  @ApiOperation(value = "Overview of Competition",
    notes = "Competition의 Overview를 반환한다.",
    response = classOf[Competition],
    responseContainer = "List")
  def getOverview(id: Long) = Action.async { implicit request =>
    competitionOverviewRepository.getByCompetitionId(id).map { competitionOverview =>
      Ok(Json.toJson(competitionOverview.head))
    }
  }

}
