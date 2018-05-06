package services

import javax.inject.Inject
import javax.inject.Singleton

import models.authentication.{User, UserRepository}

import scala.concurrent.Future

/**
 * @author Lawrence
 * @note
 * @since 2018. 5. 6.
 */
 @Singleton
class UserService @Inject()(userRepository: UserRepository) {

  def get(userId: Option[Long]): Future[Option[User]] = {
    userRepository.select(userId)
  }
}
