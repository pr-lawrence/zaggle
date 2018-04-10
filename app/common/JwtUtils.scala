package common

import java.time.{LocalDateTime, ZoneId}
import java.util.Date

import com.typesafe.config._
import pdi.jwt._
import play.api.libs.json._

import scala.concurrent.duration._
import scala.util.Try
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
  private val BASIC_EXPIRY_TIME = 60 minutes

  def tokenOf(datas: Map[String, String]): String= {
    encode(Json.toJsObject(datas), JWT_DEFAULT_OPTION)
  }

  def tokenOf(jsObject: JsObject): String = {
    encode(jsObject, JWT_DEFAULT_OPTION)
  }

  private def encode(claims: JsObject, options: JsObject): String = {
    JwtJson.encode(claims.deepMerge(options), KEY, ALGORITHM)
  }

  def decode(token:String): Try[JwtClaim] = {
    JwtJson.decode(token, KEY, Seq(ALGORITHM))
  }

  /**
    * jwt default option
    * @return
    */
  private def JWT_DEFAULT_OPTION: JsObject = {
    JsObject(Seq(
      "iss" -> JsString("zaggle"),
      "exp" -> JsNumber(ldtAfter(LocalDateTime.now, BASIC_EXPIRY_TIME).getTime),
      "sub" -> JsString("access")
    ))
  }

  private def ldtAfter(ldt: LocalDateTime, afterMinutes: FiniteDuration): Date = {
    Date.from(ldt.plusMinutes(afterMinutes.length).atZone(ZoneId.systemDefault).toInstant)
  }
}
