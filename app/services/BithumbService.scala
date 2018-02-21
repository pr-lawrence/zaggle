package services

import javax.inject._

/**
  * @author Lawrence
  * @since 2018. 2. 12.
  * @note
  * @version 0.1.0
  */
@Singleton
class BithumbService @Inject()() {

  def get() = {
    "Hello world"
  }
}
