package models.competition

import java.time.LocalDateTime

import models.competition.CompetitionStateType.{CompetitionStateType, _}
import play.api.libs.json._
import play.api.libs.json.Reads._
import play.api.libs.functional.syntax._

/**
  *
  * @author: Lawrence
  * @since: 2018. 2. 4.
  * @note:
  * @version: 0.1.0
  */
case class Competition(competId: String,
                       examinerId: String,
                       headline: String,
                       description: String,
                       imgUrl: String,
                       stateTp: Long,
                       participantCnt: Long,
                       reward: Long,
                       regiDate: LocalDateTime = LocalDateTime.now)

object Competition {

  //  implicit val format = Json.format[Competition]
  implicit val format: Format[Competition] = (
    (JsPath \ "competId").format[String] and
      (JsPath \ "examinerId").format[String] and
      (JsPath \ "headline").format[String] and
      (JsPath \ "description").format[String] and
      (JsPath \ "imgUrl").format[String] and
      (JsPath \ "stateTp").format[Long] and
      (JsPath \ "participantCnt").format[Long] and
      (JsPath \ "reward").format[Long] and
      (JsPath \ "regiDate").format[LocalDateTime]
    ) (Competition.apply, unlift(Competition.unapply))
}