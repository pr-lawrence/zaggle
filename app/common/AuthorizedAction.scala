package common

import java.util.Date
import java.time._
import javax.inject._

import models.common.ZaggleRequestContext
import play.api.libs.json.Json
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

      // token 존재 여부
      JwtUtils.decode(token) match {
        case Success(jwtClaim) =>
          val exp = jwtClaim.expiration

          // 만료시간 확인
          if(exp.isDefined){
            val expLocalDateTime = LocalDateTime.ofInstant(new Date(exp.get).toInstant, ZoneId.systemDefault)

            // 만료시간 경과 여부 확인
            if(LocalDateTime.now isBefore expLocalDateTime) {
              action(request.addAttr(AuthorizedAction.JWT_KEY, Json.parse(jwtClaim.content).as[ZaggleRequestContext]))
            } else {
              Future.successful(BadRequest("토큰의 유효시간이 만료 되었습니다."))
            }
          } else {
            Future.successful(BadRequest("토큰의 유효시간이 존재하지 않습니다."))
          }
        case _ =>
          Future.successful(Unauthorized("허용되지 않는 요청입니다."))
      }
    } else {
      Future.successful(Unauthorized("허용되지 않는 요청입니다."))
    }
  }
}

object AuthorizedAction {
  val JWT_KEY: TypedKey[ZaggleRequestContext] = TypedKey.apply[ZaggleRequestContext]("jwt")
}

class AuthorizedAction @Inject()(parser: BodyParsers.Default)(implicit ec: ExecutionContext) extends ActionBuilderImpl(parser) {
  override def invokeBlock[A](request: Request[A], block: (Request[A]) => Future[Result]) = {
    block(request)
  }

  override def composeAction[A](action: Action[A]) = new Authorized(action)
}
