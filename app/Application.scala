import play.api.mvc._
import akka.actor._
import javax.inject._

import actors.BithumbActor

/**
  *
  * @author Lawrence
  * @since 2018. 2. 12.
  * @note
  * @version 0.1.0
  */
@Singleton
class Application @Inject() (system: ActorSystem){
  val helloActor = system.actorOf(BithumbActor.props, "hello-actor")
}
