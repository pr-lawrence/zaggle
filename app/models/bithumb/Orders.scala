package models.bithumb

import play.api.libs.json.Json

/**
  *
  * @author Lawrence
  * @since 2017. 11. 20.
  * @note
  * @version 0.1.1
  */
/*
{
  "status": "0000",
  "data": [
    {
      "order_id": "1511179593025365",
      "order_currency": "QTUM",
      "order_date": "1511179593025365",
      "payment_currency": "KRW",
      "type": "bid",
      "status": "placed",
      "units": "0.1",
      "units_remaining": "0.1",
      "price": "15800",
      "misu_yn": "N",
      "fee": null,
      "total": null,
      "date_completed": null
    }
  ]
}
 */
case class OrdersData(
                       orderId: String,
                       orderCurrency: String,
                       orderDate: String,
                       paymentCurrency: String,
                       `type`: String,
                       status: String,
                       units: String,
                       unitsRemaining: String,
                       price: String,
                       misuYn: String,
                       fee: Option[String],
                       total: Option[String],
                       dateCompleted: Option[String])

object OrdersData{
  implicit val format = Json.format[OrdersData]
}

case class Orders(status: String, data: List[OrdersData])

object Orders{
  implicit val format = Json.format[Orders]
}