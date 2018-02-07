package services.competition

import javax.inject._

import models.competition.{Competition, CompetitionRepository}

/**
  *
  * @author: Lawrence
  * @since: 2018. 2. 4.
  * @note:
  */
@Singleton
class CompetitionService @Inject() (repos: CompetitionRepository){

  def list(): List[Competition] = {
    repos.select()
  }

  def get(id: String): Option[Competition] = {
    repos.findById(id)
  }
}