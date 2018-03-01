package controllers

import javax.inject._

import play.api._
import play.api.mvc._
import common._

/**
  *
  * @author Lawrence
  * @since 2018. 2. 24.
  * @note
  * @version 0.1.1
  */
@Singleton
class AccountController @Inject()(cc: ControllerComponents, authorizedAction: AuthorizedAction,
                                 ) extends AbstractController(cc) {

  def me() = authorizedAction { request =>
    Logger.info(s"${request.attrs.get(AuthorizedAction.JWT_KEY)}")
    Ok("Hello")
  }
}
