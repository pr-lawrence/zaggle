package common

import java.time.{LocalDateTime, ZoneId}
import java.util.Date

import com.typesafe.config.{Config, ConfigFactory}
import models.authentication.AuthToken
import pdi.jwt._
import play.api.libs.json._

import scala.concurrent.duration._
/**
  *
  * @author Lawrence
  * @since 2018. 2. 21.
  * @note
  * @version 0.1.1
  */
object JwtUtils{

  private val config: Config = ConfigFactory.load()

  private val KEY = config.getString("play.http.secret.key")
  private val ALGORITHM = JwtAlgorithm.HS256
  private val BASIC_EXPIRY_TIME = 10 minutes

  def tokenOf(datas: Map[String, String]): String= {
    encode(Json.toJsObject(datas), JWT_OPTION)
  }

  def tokenOf(jsObject: JsObject): String = {
    encode(jsObject, JWT_OPTION)
  }

  private def encode(claims: JsObject, options: JsObject): String = {
    JwtJson.encode(claims.deepMerge(options), KEY, ALGORITHM)
  }

  private def JWT_OPTION: JsObject = {
    JsObject(Seq(
      "iss" -> JsString("zaggle"),
      "exp" -> JsNumber(Date.from(LocalDateTime.now.plusMinutes(BASIC_EXPIRY_TIME.length).atZone(ZoneId.systemDefault).toInstant).getTime),
      "sub" -> JsString("access")
    ))
  }
}
