package actors

/**
  *
  * @author Lawrence
  * @since 2018. 2. 12.
  * @note
  * @version 0.1.0
  */

import akka.actor._

import scala.concurrent.duration._

object BithumbSupervisor {
  def props = Props[BithumbSupervisor]


  sealed trait BithymbActorCommand
  case object Tick extends BithymbActorCommand
  case class SayHello(name: String) extends BithymbActorCommand
}

class BithumbSupervisor extends Actor with ActorLogging {

  import BithumbSupervisor._
  import context.dispatcher

  override def preStart(): Unit = {
    println("I'm alive!")
    context.actorOf(BithumbCrawler.props)
  }

  def receive = {
    case Tick =>

    case SayHello(name: String) =>
      sender() ! "Hello, " + name
  }
}