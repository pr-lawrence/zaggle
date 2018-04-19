package models.discussion

import java.time.LocalDateTime

import play.api.libs.json.Json

/**
  *
  * @author Lawrence
  * @since 2018. 4. 20.
  * @note
  * @version 0.0.2
  */
case class Comment(commentId: Long
                   , relationId: Long
                   , commentType: String
                   , comment: String
                   , author: String
                   , regiDate: LocalDateTime
                   , editDate: Option[LocalDateTime]
                  )

object Comment {
  implicit val format = Json.format[Comment]
}
/**
  * create table zaggle.tb_comment (
  * comment_id   INT           NOT NULL,
  * relation_id  INT           NOT NULL,
  * comment_type VARCHAR(32)   NOT NULL,
  * comment      VARCHAR(4096) NOT NULL,
  * author       VARCHAR(256)  NOT NULL,
  * regi_date    DATE          NOT NULL DEFAULT CURRENT_TIMESTAMP,
  * edit_date    DATE          NULL,
  * PRIMARY KEY (comment_id)
  * );
  */