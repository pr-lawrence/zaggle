package models.competition

import play.api.libs.json._
import play.api.libs.functional.syntax._

/**
  * @author Lawrence
  * @since 2018. 2. 5.
  * @note
  * @version 0.1.0
  */

object CompetitionStateType {

  def valueOf(stateTp: Long): CompetitionStateType = {
    stateTp match {
      case 1L =>
        Featured
      case 2L =>
        Playground
    }
  }

  sealed abstract class CompetitionStateType(`type`: Int, name: String)

  case object Featured extends CompetitionStateType(1, "Featured")

  case object Playground extends CompetitionStateType(2, "Playground")
}