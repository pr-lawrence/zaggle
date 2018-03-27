package services

import java.time.LocalDateTime
import javax.inject.{Inject, Singleton}

import com.typesafe.config.ConfigFactory
import models.bithumb.CoinType.Coin
import models.bithumb._
import play.api.libs.json.{JsValue, Json}
import play.api.libs.ws.WSClient

import scala.concurrent.{ExecutionContext, Future}

/**
  *
  * @author Lawrence
  * @since 2018. 3. 14.
  * @note
  * @version 0.1.1
  */
@Singleton
class BithumbApi @Inject()(ws: WSClient)(implicit ec: ExecutionContext) {

  private val config = ConfigFactory.load()
  private val baseUrl: String = config.getString("baseUrl")
  private val apiKey: String = config.getString("apiKey")
  private val secretKey: String = config.getString("secretKey")

  /**
    * API  : https://api.bithumb.com/public/ticker/{currency}
    * TYPE : Public
    * NOTE : bithumb 거래소 마지막 거래 정보
    */
  def ticker(currency: Coin): Future[Ticker] = {
    val api = List(baseUrl, "public/ticker", currency.value).mkString("/")

    ws.url(api)
      .get()
      .mapTo[Ticker]
  }

  /**
    * API  : https://api.bithumb.com/public/ticker/ALL
    * TYPE : Public
    * NOTE : bithumb 거래소 마지막 거래 정보
    */
  def tickerAll: Future[TickerAll] = {
    val api = List(baseUrl, "public/ticker/ALL").mkString("/")

    ws.url(api)
      .get()
      .map { response =>


        val jValue: JsValue = Json.parse(response.body)
        val status: String = (jValue \ "status").as[String]
        val date: String = (jValue \ "data" \ "date").as[String]

        (jValue \ "data" \ "BTC").as[TickerWithoutDate]

        val now = LocalDateTime.now

        TickerAll(status,
          Map(
            "BTC" -> TickerData.from((jValue \ "data" \ "BTC").as[TickerWithoutDate], now),
            "ETH" -> TickerData.from((jValue \ "data" \ "ETH").as[TickerWithoutDate], now),
            "DASH" -> TickerData.from((jValue \ "data" \ "DASH").as[TickerWithoutDate], now),
            "LTC" -> TickerData.from((jValue \ "data" \ "LTC").as[TickerWithoutDate], now),
            "ETC" -> TickerData.from((jValue \ "data" \ "ETC").as[TickerWithoutDate], now),
            "XRP" -> TickerData.from((jValue \ "data" \ "XRP").as[TickerWithoutDate], now),
            "BCH" -> TickerData.from((jValue \ "data" \ "BCH").as[TickerWithoutDate], now),
            "XMR" -> TickerData.from((jValue \ "data" \ "XMR").as[TickerWithoutDate], now),
            "ZEC" -> TickerData.from((jValue \ "data" \ "ZEC").as[TickerWithoutDate], now),
            "QTUM" -> TickerData.from((jValue \ "data" \ "QTUM").as[TickerWithoutDate], now)))

      }
  }
}
