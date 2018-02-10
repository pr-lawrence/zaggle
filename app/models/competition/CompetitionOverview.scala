package models.competition

import java.time.LocalDateTime

import play.api.libs.json._
/**
  *
  * @author: Lawrence
  * @since: 2018. 2. 9.
  * @note: Competition Overview
  * @version: 0.1.0
  */
case class CompetitionOverview(competitionOverviewId: Long,
                               competitionId: Long,
                               description: String,
                               evaluation: String,
                               prizes: String,
                               kernelsFaq: String,
                               timeline: String,
                               regiDate: LocalDateTime = LocalDateTime.now)

object CompetitionOverview {
  implicit val format = Json.format[CompetitionOverview]
}