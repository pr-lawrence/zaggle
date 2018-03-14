package models.bithumb

import play.api.libs.json.Json

/**
  *
  * @author Lawrence
  * @since 2017. 11. 18.
  * @note
  * @version 0.1.1
  */
case class WalletAddressData(walletAddress: String, currency: String)

object WalletAddressData {
  implicit val format = Json.format[WalletAddressData]
}

case class WalletAddress(status: String, data: WalletAddressData)

object WalletAddress {
  implicit val format = Json.format[WalletAddress]
}

/*
{
  "status": "0000",
  "data": {
    "wallet_address": "",
    "currency": "BTC"
  }
}
*/