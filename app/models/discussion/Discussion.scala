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
                      , title: String
                      , content: String
                      , author: String
                      , subject: String
                      , regiDate: LocalDateTime = LocalDateTime.now
                      , editDate: Option[LocalDateTime])

object Discussion {
  implicit val format = Json.format[Discussion]
}