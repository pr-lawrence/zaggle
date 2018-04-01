package services

import javax.inject._

import models.bithumb.TickerRepository

/**
  * @author Lawrence
  * @since 2018. 2. 12.
  * @note
  * @version 0.1.0
  */
@Singleton
class BithumbService @Inject()(tickerRepos: TickerRepository) {

  def get() = {
    "Hello world"
  }

  def list(coinType: String, size: Int) = {
    tickerRepos.list(coinType, size)
  }
}
