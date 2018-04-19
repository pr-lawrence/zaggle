package services

import javax.inject.{Inject, Singleton}

import models.discussion._
import scala.concurrent._

/**
  *
  * @author Lawrence
  * @since 2018. 4. 20.
  * @note
  * @version 0.2.0
  */
@Singleton
class CommentService @Inject()(commentRepos: CommentRepository) {

  def list(): Future[Seq[Comment]] = {
    commentRepos.select()
  }

  def getById(id: Long): Future[Option[Comment]] = {
    commentRepos.selectById(id)
  }

  def getByTypeNRelationId(commentType: String, relationId: Long): Future[Seq[Comment]] = {
    commentRepos.selectBytypeNRelationId(commentType.toUpperCase, relationId)
  }

  def create(comment: Comment): Future[Comment] = {
    commentRepos.insert(comment)
  }

}
