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
    "order_id"  : "1428646963419",
    "data": [
        {
            "cont_id"   : "15313",
            "units"     : "0.61460000",
            "price"     : "284000",
            "total"     : 174546,
            "fee"       : "0.00061460"
        },
        {
            "cont_id"   : "15314",
            "units"     : "0.18540000",
            "price"     : "289000",
            "total"     : 53581,
            "fee"       : "0.00018540"
        }
    ]
}


 */
case class PlaceData(contId: String, units: String, price: String, total: Long, fee: String)

object PlaceData{
  implicit val format = Json.format[PlaceData]
}

case class Place(status: String, orderId: String, data: List[PlaceData])

object Place{
  implicit val format = Json.format[Place]
}