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
      "total_krw": 51511,
      "in_use_krw": 0,
      "available_krw": 51511,
      "misu_krw": 0,
      "total_btc": "0.00000000",
      "in_use_btc": "0.00000000",
      "available_btc": "0.00000000",
      "misu_btc": "0.00000000",
      "xcoin_last": "10915000"
    }
}
*/
case class BalanceData(
                        totalKrw: Int,
                        inUseKrw: Int,
                        availableKrw: Int,
                        misuKrw: Int,
                        misuDepoKrw: Option[Int],
                        totalBtc: String,
                        inUseBtc: String,
                        availableBtc: String,
                        misuBtc: String,
                        xcoinLast: String
                      )

object BalanceData{
  implicit val format = Json.format[BalanceData]
}

case class Balance(status: String, data: BalanceData)

object Balance{
  implicit val format = Json.format[Balance]
}