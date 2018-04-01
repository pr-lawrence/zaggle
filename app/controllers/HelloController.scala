package controllers

import java.time.LocalDateTime
import javax.inject._

import io.swagger.annotations.Api
import models._
import models.bithumb.{TickerData, TickerRepository}
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}
import scala.util._

/**
  *
  * @author Lawrence
  * @since 2018. 1. 22.
  * @note Monitoring Controller
  * @version 0.1.0
  */
@Api(value = "Monitoring")
@Singleton
class HelloController @Inject()(cc: ControllerComponents, tickerRepos: TickerRepository, coffeeRepos: CoffeeRepository)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  def l7check() = Action {
    Ok(Json.toJson(Map("Hello" -> "world"))).withHeaders(
      CACHE_CONTROL -> "max-age=3600",
      ETAG -> "xx")
  }

  def get() = TODO
}
