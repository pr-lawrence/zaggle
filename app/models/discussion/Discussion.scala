package models.discussion

import java.time.LocalDateTime

import play.api.libs.json.Json

/**
  *
  * @author Lawrence
  * @since 2018. 4. 5.
  * @note
  * @version
  */
case class Discussion(discusId: Long
                      , competId: Long
                      , title: String
                      , content: String
                      , author: String
                      , subject: String
                      , delFlag: Option[Boolean] = Some(false)
                      , regiDate: Option[LocalDateTime] = Some(LocalDateTime.now)
                      , editDate: Option[LocalDateTime]
                      , delDate: Option[LocalDateTime])

object Discussion {
  implicit val format = Json.format[Discussion]
}