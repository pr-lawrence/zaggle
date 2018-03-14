package services

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
  def tickerAll(): Future[TickerAll] = {
    val api = List(baseUrl, "public/ticker/ALL").mkString("/")

    ws.url(api)
      .get()
      .map { response =>
        val jValue: JsValue = Json.parse(response.body)
        val status: String = (jValue \ "status").as[String]
        val date: String = (jValue \ "data" \ "date").as[String]


        (jValue \ "data" \ "BTC").as[TickerWithoutDate]

        TickerAll(status,
          Map(
            "BTC" -> TickerData.from((jValue \ "data" \ "BTC").as[TickerWithoutDate], date),
            "ETH" -> TickerData.from((jValue \ "data" \ "ETH").as[TickerWithoutDate], date),
            "DASH" -> TickerData.from((jValue \ "data" \ "DASH").as[TickerWithoutDate], date),
            "LTC" -> TickerData.from((jValue \ "data" \ "LTC").as[TickerWithoutDate], date),
            "ETC" -> TickerData.from((jValue \ "data" \ "ETC").as[TickerWithoutDate], date),
            "XRP" -> TickerData.from((jValue \ "data" \ "XRP").as[TickerWithoutDate], date),
            "BCH" -> TickerData.from((jValue \ "data" \ "BCH").as[TickerWithoutDate], date),
            "XMR" -> TickerData.from((jValue \ "data" \ "XMR").as[TickerWithoutDate], date),
            "ZEC" -> TickerData.from((jValue \ "data" \ "ZEC").as[TickerWithoutDate], date),
            "QTUM" -> TickerData.from((jValue \ "data" \ "QTUM").as[TickerWithoutDate], date)))

      }
  }
}
