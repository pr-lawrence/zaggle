package models.bithumb

import java.sql.Date
import java.time.{LocalDateTime, LocalTime}
import javax.inject._

import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

/**
  *
  * @author Lawrence
  * @since 2018. 3. 31.
  * @note
  * @version 0.1.0
  */
@Singleton
class TickerRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {

  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  private class TickerTable(tag: Tag) extends Table[TickerData](tag, "tb_ticker") {

    implicit val localDateToDate = MappedColumnType.base[LocalDateTime, Date](
      ldt => Date.valueOf(ldt.toLocalDate()),
      d => LocalDateTime.of(d.toLocalDate, LocalTime.MIDNIGHT)
    )

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def coinType = column[String]("coin_type")

    def openingPrice = column[Long]("opening_price")

    def closingPrice = column[Long]("closing_price")

    def minPrice = column[Long]("min_price")

    def maxPrice = column[Long]("max_price")

    def averagePrice = column[Double]("average_price")

    def unitsTraded = column[Double]("units_traded")

    def volume1day = column[Double]("volume_1day")

    def volume7day = column[Double]("volume_7day")

    def buyPrice = column[Long]("buy_price")

    def sellPrice = column[Long]("sell_price")

    def regDate = column[LocalDateTime]("reg_date")

    def * = (id, coinType, openingPrice, closingPrice, minPrice, maxPrice, averagePrice, unitsTraded, volume1day, volume7day, buyPrice, sellPrice, regDate) <> ((TickerData.apply _).tupled, TickerData.unapply)
  }

  private val ticker = TableQuery[TickerTable]

  def list(): Future[Seq[TickerData]] = db.run {
    ticker.result
  }

  def list(coinType: String, size: Int): Future[Seq[TickerData]] = db.run {
    ticker.filter(_.coinType === coinType)
      .sortBy(_.id.desc)
      .take(size)
      .result
  }

  def create (tParam: TickerData) = db.run {
    implicit val localDateToDate = MappedColumnType.base[LocalDateTime, Date](
      ldt => Date.valueOf(ldt.toLocalDate()),
      d => LocalDateTime.of(d.toLocalDate, LocalTime.MIDNIGHT)
    )
    (ticker.map(t => (t.coinType, t.openingPrice, t.closingPrice, t.minPrice, t.maxPrice, t.averagePrice, t.unitsTraded, t.volume1day, t.volume7day, t.buyPrice, t.sellPrice, t.regDate))
      returning ticker.map(_.id)
      into ((tData, id) => TickerData(id, tData._1, tData._2, tData._3, tData._4, tData._5, tData._6, tData._7, tData._8, tData._9, tData._10, tData._11, tData._12))
      ) += (tParam.coinType, tParam.openingPrice, tParam.closingPrice, tParam.minPrice, tParam.maxPrice, tParam.averagePrice, tParam.unitsTraded, tParam.volume1day, tParam.volume7day, tParam.buyPrice, tParam.sellPrice, tParam.regDate)
  }
}
