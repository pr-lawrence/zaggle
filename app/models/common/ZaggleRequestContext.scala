package models.common

import play.api.libs.json.Json

/**
  *
  * @author Lawrence
  * @since 2018. 2. 25.
  * @note
  * @version
  */
case class ZaggleRequestContext(userId: Long, loginId: String, avatarUrl: String)

object ZaggleRequestContext{
  implicit val format = Json.format[ZaggleRequestContext]
}