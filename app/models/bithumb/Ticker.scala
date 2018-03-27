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

/*
{
    "status": "0000",
    "data": {
        "opening_price" : "504000",
        "closing_price" : "505000",
        "min_price"     : "504000",
        "max_price"     : "516000",
        "average_price" : "509533.3333",
        "units_traded"  : "14.71960286",
        "volume_1day"   : "14.71960286",
        "volume_7day"   : "15.81960286",
        "buy_price"     : "505000",
        "sell_price"    : "504000",
        "date"          : 1417141032622
    }
}
*/

case class TickerData(
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
                       date: LocalDateTime
                     )

object TickerData {
  def from(tickerWithoutDate: TickerWithoutDate, date: LocalDateTime): TickerData = {
    TickerData(
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
  import play.api.libs.json._ // JSON library
  import play.api.libs.json.Reads._ // Custom validation helpers
  import play.api.libs.functional.syntax._ // Combinator syntax

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

/*
case class Ticker(
                         openingPrice: BigInt,
                         closingPrice: BigInt,
                         minPrice: BigInt,
                         maxPrice: BigInt,
                         averagePrice: BigInt,
                         unitsTraded: BigInt,
                         volume1day: BigInt,
                         volume7day: BigInt,
                         buyPrice: BigInt,
                         sellPrice: BigInt,
                         date: String
                       ) {

  def this(openingPrice: String,
           closingPrice: String,
           minPrice: String,
           maxPrice: String,
           averagePrice: String,
           unitsTraded: String,
           volume1day: String,
           volume7day: String,
           buyPrice: String,
           sellPrice: String,
           date: String) =
    this(
      BigInt((openingPrice.toDouble * 100000000).toInt),
      BigInt((closingPrice.toDouble * 100000000).toInt),
      BigInt((minPrice.toDouble * 100000000).toInt),
      BigInt((maxPrice.toDouble * 100000000).toInt),
      BigInt((averagePrice.toDouble * 100000000).toInt),
      BigInt((unitsTraded.toDouble * 100000000).toInt),
      BigInt((volume1day.toDouble * 100000000).toInt),
      BigInt((volume7day.toDouble * 100000000).toInt),
      BigInt((buyPrice.toDouble * 100000000).toInt),
      BigInt((sellPrice.toDouble * 100000000).toInt),
      date)
}

*/