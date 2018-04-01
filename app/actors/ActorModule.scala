package actors

import com.google.inject.AbstractModule
import play.api.libs.concurrent.AkkaGuiceSupport

/**
  *
  * @author Lawrence
  * @since 2018. 3. 19.
  * @note
  * @version 0.1.1
  */
class ActorModule extends AbstractModule with AkkaGuiceSupport {
  def configure = {
    bindActor[BithumbCrawler]("bithumb-crawler")
  }
}