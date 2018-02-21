package controllers

import javax.inject.{Inject, Singleton}

import play.api.mvc.{AbstractController, ControllerComponents}

/**
  *
  * @author Lawrence
  * @since 2018. 2. 21.
  * @note
  * @version
  */
@Singleton
class AuthenticationController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def local = Action { implicit request =>
    Ok("")
  }
}
