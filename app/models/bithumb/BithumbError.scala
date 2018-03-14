package models.bithumb

import play.api.libs.json.Json

/**
  *
  * @author Lawrence
  * @since 2017. 11. 20.
  * @note
  * @version 0.1.1
  */
case class BithumbError(status: String, message: String)

object BithumbError{
  implicit val format = Json.format[BithumbError]
}