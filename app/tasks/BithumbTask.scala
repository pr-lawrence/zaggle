package tasks

import javax.inject._

import actors.BithumbActor
import akka.actor._

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

/**
  *
  * @author Lawrence
  * @since 2018. 2. 24.
  * @note
  * @version 0.1.1
  */
@Singleton
class BithumbTask @Inject()(actorSystem: ActorSystem)(implicit executionContext: ExecutionContext) {
  actorSystem.scheduler.scheduleOnce(delay = 0 seconds) {
    actorSystem.actorOf(Props(classOf[BithumbActor]))
  }
}