package models.bithumb

import java.time.LocalDateTime

import play.api.libs.json._
import play.api.libs.functional.syntax._

/**
  *
  * @author Lawrence
  * @since 2017. 11. 13.
  * @note
  * @version 0.1.1
  */

case class TickerData(
                       id: Long,
                       coinType: String,
                       openingPrice: Long,
                       closingPrice: Long,
                       minPrice: Long,
                       maxPrice: Long,
                       averagePrice: Double,
                       unitsTraded: Double,
                       volume1day: Double,
                       volume7day: Double,
                       buyPrice: Long,
                       sellPrice: Long,
                       regDate: LocalDateTime
                     )

object TickerData {
  def from(tickerWithoutDate: TickerWithoutDate, coinType: String, date: LocalDateTime): TickerData = {
    TickerData(
      0L,
      coinType,
      tickerWithoutDate.openingPrice.toLong,
      tickerWithoutDate.closingPrice.toLong,
      tickerWithoutDate.minPrice.toLong,
      tickerWithoutDate.maxPrice.toLong,
      tickerWithoutDate.averagePrice.toDouble,
      tickerWithoutDate.unitsTraded.toDouble,
      tickerWithoutDate.volume1day.toDouble,
      tickerWithoutDate.volume7day.toDouble,
      tickerWithoutDate.buyPrice.toLong,
      tickerWithoutDate.sellPrice.toLong,
      date
    )
  }

  implicit val format = Json.format[TickerData]
}

case class TickerWithoutDate(openingPrice: String,
                             closingPrice: String,
                             minPrice: String,
                             maxPrice: String,
                             averagePrice: String,
                             unitsTraded: String,
                             volume1day: String,
                             volume7day: String,
                             buyPrice: String,
                             sellPrice: String)

object TickerWithoutDate {

  import play.api.libs.json._
  import play.api.libs.json.Reads._
  import play.api.libs.functional.syntax._

  implicit val tickerWithoutDataReads: Reads[TickerWithoutDate] = (
    (__ \ "opening_price").read[String] and
      (__ \ "closing_price").read[String] and
      (__ \ "min_price").read[String] and
      (__ \ "max_price").read[String] and
      (__ \ "average_price").read[String] and
      (__ \ "units_traded").read[String] and
      (__ \ "volume_1day").read[String] and
      (__ \ "volume_7day").read[String] and
      (__ \ "buy_price").read[String] and
      (__ \ "sell_price").read[String]) (TickerWithoutDate.apply _)
}

case class Ticker(status: String, data: TickerData)

object Ticker {
  implicit val format = Json.format[Ticker]
}

case class TickerAll(status: String, data: Map[String, TickerData])

object TickerAll {
  implicit val format = Json.format[TickerAll]
}