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

  def list: Future[Seq[Discussion]] = {
    discussionRepos.select()
  }

  def list(competitionId: Long): Future[Seq[Discussion]] = {
    discussionRepos.selectByCompetitionId(competitionId)
  }

  def getById(id: Long): Future[Option[Discussion]] = {
    discussionRepos.selectById(id)
  }

  def create(discussion: Discussion): Future[Discussion] = {
    discussionRepos.insert(discussion)
  }

  def update(discussion:Discussion): Future[Int] = {
    discussionRepos.update(discussion)
  }

  def delete(id: Long): Future[Int] = {
    discussionRepos.delete(id)
  }
}
