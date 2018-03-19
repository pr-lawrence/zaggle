package controllers

import javax.inject._

import akka.actor.ActorRef
import io.swagger.annotations.Api
import models.bithumb.TickerAll
import play.api.Logger
import play.api.libs.json.Json
import play.api.mvc._
import services.BithumbApi

import scala.concurrent.{ExecutionContext, Future}

/**
  *
  * @author Lawrence
  * @since 2018. 1. 22.
  * @note Monitoring Controller
  * @version 0.1.0
  */
@Api(value="Monitoring")
@Singleton
class HelloController @Inject()(cc: ControllerComponents, bithumbApi: BithumbApi)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  def l7check() = Action {

    val eventualAll: Future[TickerAll] = bithumbApi.tickerAll

    eventualAll.map( s =>
      Logger.info(s"$s")
    )

    Ok(Json.toJson(Map("Hello" -> "world"))).withHeaders(
      CACHE_CONTROL -> "max-age=3600",
      ETAG -> "xx")
  }

  def get() = TODO
}
