package models.bithumb

import play.api.libs.json.Json

/**
  *
  * @author Lawrence
  * @since 2017. 11. 17.
  * @note
  * @version 0.1.1
  */

/*
{
    "status"    : "0000",
    "data"      : {
        "timestamp"         : 1417142049868,
        "order_currency"    : "BTC",
        "payment_currency"  : "KRW",
        "bids": [
            {
                "quantity"  : "6.1189306",
                "price"     : "504000"
            },
            {
                "quantity"  : "10.35117828",
                "price"     : "503000"
            }
        ],
        "asks": [
            {
                "quantity"  : "2.67575",
                "price"     : "506000"
            },
            {
                "quantity"  : "3.54343",
                "price"     : "507000"
            }
        ]
    }
}
 */
case class Bids(quantity: String, price: String)

case class OrderbookData(timestamp: String, orderCurrency: String, paymentCurrency: String, bids: List[Bids], asks: List[Bids])

case class Orderbook(status: String, data: OrderbookData)

object Bids{
  implicit val format = Json.format[Bids]
}

object OrderbookData{
  implicit val format = Json.format[OrderbookData]
}

object Orderbook{
  implicit val format = Json.format[Orderbook]
}