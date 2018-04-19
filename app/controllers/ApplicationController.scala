package controllers

import javax.inject._

import models.competition.CompetitionRepository
import play.api.mvc._

/**
  *
  * @author Lawrence
  * @since 2018. 2. 6.
  * @note
  * @version 0.0.1
  */
@Singleton
class ApplicationController @Inject()(cc: ControllerComponents, competitionService: CompetitionRepository) extends AbstractController(cc) {
  def redirectDocs = Action { implicit request =>
    Redirect(url = "/assets/lib/swagger-ui/index.html", queryString = Map("url" -> Seq("/swagger.json")))
  }
}
