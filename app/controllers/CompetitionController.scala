package controllers

import javax.inject._

import io.swagger.annotations.Api
import models.competition.CompetitionRepository
import play.api.libs.json.Json
import play.api.mvc._

/**
  *
  * @author: Lawrence
  * @since: 2018. 2. 4.
  * @note:
  * @version: 0.1.0
  */
@Api
@Singleton
class CompetitionController @Inject()(cc: ControllerComponents, competitionService: CompetitionRepository) extends AbstractController(cc) {

  /**
    * list of Competition
    *
    * @return
    */
  def list() = Action { implicit request =>
    val competitions = competitionService.select()
    Ok(Json.toJson(competitions))
  }

  /**
    * Competition
    * z
    *
    * @param id
    * @return
    */
  def get(id: String) = Action(parse.json) { implicit request =>
    val maybeCompetition = competitionService.findById(id)
    maybeCompetition match {
      case Some(competition) =>
        Ok(Json.toJson(competition))
      case _ =>
        NotFound
    }
  }
}
