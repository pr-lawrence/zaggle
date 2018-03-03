package filters

import javax.inject._

import akka.stream.Materializer
import akka.util.ByteString
import play.api.Logger
import play.api.libs.streams.Accumulator
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

/**
  *
  * @author Lawrence
  * @since 2018. 2. 4.
  * @note
  */

class LoggingFilter @Inject() (implicit ec: ExecutionContext) extends EssentialFilter {
  def apply(nextFilter: EssentialAction) = new EssentialAction {
    def apply(requestHeader: RequestHeader) = {

      val startTime = System.currentTimeMillis

      val accumulator: Accumulator[ByteString, Result] = nextFilter(requestHeader)

      accumulator.map { result =>

        val endTime = System.currentTimeMillis
        val requestTime = endTime - startTime

        Logger.info(s"${requestHeader.method} ${requestHeader.uri} took ${requestTime}ms and returned ${result.header.status}")

        result.withHeaders("Request-Time" -> requestTime.toString)

      }
    }
  }
}