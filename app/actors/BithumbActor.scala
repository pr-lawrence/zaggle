package actors

/**
  *
  * @author Lawrence
  * @since 2018. 2. 12.
  * @note
  * @version 0.1.0
  */

import akka.actor._

object BithumbActor {
  def props = Props[BithumbActor]

  case class SayHello(name: String)
}

class BithumbActor extends Actor {

  import BithumbActor._

  override def preStart(): Unit = {
    println("I'm alive!")
  }

  def receive = {
    case SayHello(name: String) =>
      sender() ! "Hello, " + name
  }
}
