package actors

/**
  *
  * @author Lawrence
  * @since 2018. 2. 12.
  * @note
  * @version 0.1.0
  */

import akka.actor._
import play.api.Logger

import scala.concurrent.duration._

object BithumbActor {
  def props = Props[BithumbActor]


  sealed trait BithymbActorCommand
  case object Tick extends BithymbActorCommand
  case class SayHello(name: String) extends BithymbActorCommand
}

class BithumbActor extends Actor {

  import BithumbActor._
  import context.dispatcher

  override def preStart(): Unit = {
    println("I'm alive!")
    context.system.scheduler.schedule(0 seconds, 1 seconds)( self ! Tick)
  }

  def receive = {
    case Tick =>

    case SayHello(name: String) =>
      sender() ! "Hello, " + name
  }
}