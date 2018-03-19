package actors

import com.google.inject.AbstractModule
import play.api.libs.concurrent.AkkaGuiceSupport

/**
  *
  * @author Lawrence
  * @since 2018. 3. 19.
  * @note
  * @version
  */
class ActorModule extends AbstractModule with AkkaGuiceSupport {
  def configure = {
    bindActor[BithumbSupervisor]("bithumb-supervisor")
    bindActor[BithumbCrawler]("bithumb-crawler")
  }
}