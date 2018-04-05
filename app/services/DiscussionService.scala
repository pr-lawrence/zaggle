package services

import javax.inject._

import models.discussion._
import scala.concurrent._

/**
  *
  * @author Lawrence
  * @since 2018. 4. 5.
  * @note
  * @version 0.1.1
  */

@Singleton
class DiscussionService @Inject()(discussionRepos: DiscussionRepository) {

  def list(): Future[Seq[Discussion]] = {
    discussionRepos.select()
  }

  def getById(id: Long): Future[Option[Discussion]] = {
    discussionRepos.selectById(id)
  }
}
