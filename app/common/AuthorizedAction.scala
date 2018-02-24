package common

import javax.inject._

import play.api._
import play.api.libs.typedmap.TypedKey
import play.api.mvc._
import play.api.mvc.Results._

import scala.concurrent._
import scala.util._

/**
  *
  * @author Lawrence
  * @since 2018. 2. 24.
  * @note
  * @version 0.1.1
  */
case class Authorized[A](action: Action[A]) extends Action[A] {

  override def parser: BodyParser[A] = action.parser

  override def executionContext: ExecutionContext = action.executionContext

  def apply(request: Request[A]): Future[Result] = {
    val accessToken = request.headers.get("authorization")
    if (accessToken.isDefined) {
      val Array(tokenType, token) = accessToken.get.split("\\s")
      JwtUtils.decode(token) match {
        case Success(jwtClaim) =>
          //          action(request.addAttr(TypedKey("a"), jwtClaim))
          action(request)
        case _ =>
          Future.successful(Unauthorized(""))
      }
    } else {
      Future.successful(BadRequest(""))
    }
  }
}

class AuthorizedAction @Inject()(parser: BodyParsers.Default)(implicit ec: ExecutionContext) extends ActionBuilderImpl(parser) {
  override def invokeBlock[A](request: Request[A], block: (Request[A]) => Future[Result]) = {
    block(request)
  }

  override def composeAction[A](action: Action[A]) = new Authorized(action)
}
