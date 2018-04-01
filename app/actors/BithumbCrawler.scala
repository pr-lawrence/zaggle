package actors

import javax.inject.Inject

import akka.actor.{Actor, Cancellable, Props}
import models.bithumb.TickerRepository
import play.api.Logger
import services.BithumbApi

import scala.concurrent.duration._

/**
  *
  * @author Lawrence
  * @since 2018. 3. 5.
  * @note
  * @version
  */

object BithumbCrawler {
  def props = Props(classOf[BithumbCrawler])

  sealed trait BithumbCrawlerCommand

  case object Tick extends BithumbCrawlerCommand
}

class BithumbCrawler @Inject() (bithumbApi: BithumbApi, tickerRepos: TickerRepository) extends Actor{

  import BithumbCrawler._
  import context.dispatcher

  private var cancellable: Cancellable = context.system.scheduler.schedule(0 seconds, 1 seconds)(self ! Tick)

  override def preStart(): Unit = {
//    log.info("Hello")
  }

  def receive = {
    case Tick =>
      for (tickerAll <- bithumbApi.tickerAll) {
        tickerAll.data.foreach(t => tickerRepos.create(t._2))
      }
  }
}
