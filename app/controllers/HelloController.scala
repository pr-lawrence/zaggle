package controllers

import javax.inject._

import io.swagger.annotations.Api
import play.api.mvc._

/**
  *
  * @author Lawrence
  * @since 2018. 1. 22.
  * @note Monitoring Controller
  * @version 0.1.0
  */
@Api(value="Monitoring")
@Singleton
class HelloController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def l7check() = Action { implicit request: Request[AnyContent] =>
    Ok("world")
  }
}
