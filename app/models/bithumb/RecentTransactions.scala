package models.bithumb

import play.api.libs.json.Json

/**
  *
  * @author Lawrence
  * @since 2017. 11. 17.
  * @note
  * @version 0.1.1
  */
case class RecentTransactionsData(
                                   transactionDate: String,
                                   `type`: String,
                                   unitsTraded: String,
                                   price: String,
                                   total: String)

object RecentTransactionsData{
  implicit val format = Json.format[RecentTransactionsData]
}

case class RecentTransactions(status: String, data: List[RecentTransactionsData])

object RecentTransactions{
  implicit val format = Json.format[RecentTransactions]
}