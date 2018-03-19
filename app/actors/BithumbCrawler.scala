package actors

import javax.inject.Inject

import akka.actor.{Actor, Cancellable, Props}
import play.api.Logger
import play.api.libs.ws.WSClient
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
  case object Start extends BithumbCrawlerCommand
  case object Stop extends BithumbCrawlerCommand
}

class BithumbCrawler @Inject() (bithumbApi: BithumbApi) extends Actor {

  import BithumbCrawler._
  import context.dispatcher

  private var cancellable: Cancellable = context.system.scheduler.schedule(0 seconds, 1 seconds)(self ! Tick)

  def receive = {
    case Tick =>
      bithumbApi.tickerAll.map(println)
    case Start =>
      cancellable.isCancelled match {
        case true =>
          cancellable = context.system.scheduler.schedule(0 seconds, 1 seconds)(self ! Start)
        case _ =>
          Logger.info("bithumb-crawler is already running.")
      }
    case Stop =>
      cancellable.isCancelled match {
        case false  =>
          cancellable.cancel()
        case true=>
          Logger.info("bithumb-crawler was already cancelled.")
      }
  }
}
