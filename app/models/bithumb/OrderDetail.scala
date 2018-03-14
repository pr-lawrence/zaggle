package models.bithumb

import play.api.libs.json.Json

/**
  *
  * @author Lawrence
  * @since 2017. 11. 18.
  * @note
  * @version 0.1.1
  */

/*
{
    "status"    : "0000",
    "data"      : [
        {
            "transaction_date"  : "1428024598967",
            "type"              : "ask",
            "order_currency"    : "BTC",
            "payment_currency"  : "KRW",
            "units_traded"      : "0.0017",
            "price"             : "264000",
            "fee"               : "0.0000017",
            "total"             : "449"
        }
    ]
}


 */
case class OrderDetailData(transactionDate: String,
                           orderCurrency: String,
                           paymentCurrency: String,
                           unitsTraded: String,
                           price: String,
                           fee: String,
                           total: String)

case class OrderDetail(status: String, data: List[OrderDetailData])

object OrderDetailData {
  implicit val format = Json.format[OrderDetailData]
}

object OrderDetail {
  implicit val format = Json.format[OrderDetail]
}

