package controllers

import javax.inject._

import play.api.mvc._
import services.BithumbService

import scala.concurrent.ExecutionContext

/**
  *
  * @author Lawrence
  * @since 2018. 1. 22.
  * @note
  * @version 0.1.0
  */
@Singleton
class BithumbController @Inject()(cc: ControllerComponents,
                                  bithumbService: BithumbService)
                                 (implicit ec: ExecutionContext) extends AbstractController(cc) {

  case class Student(age: Int, name: String)

  import play.api.libs.json._

  implicit val studentFormat = Json.format[Student]

  def index() = Action(parse.json) { implicit request =>
    request.body.asOpt[List[Student]] match {
      case Some(students) =>
        Ok(Json.toJson(students))
      case None =>
        BadRequest("")
    }
  }

  def get() = Action { implicit request =>
    println(bithumbService.get())
    Ok("")
  }

  def list(coinType: String, size: Int) = Action.async { implicit request =>
    bithumbService.list(coinType, size).map { tickers =>
      Ok(Json.toJson(tickers))
    }
  }
}