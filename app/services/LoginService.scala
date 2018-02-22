package services

import javax.inject.{Inject, Singleton}

import models.authentication.{LoginRequest, User, UserRepository}

import scala.concurrent.{Future, Promise}

/**
  *
  * @author Lawrence
  * @since 2018. 2. 22.
  * @note
  * @version
  */
@Singleton
class LoginService @Inject()(userRepository: UserRepository){

  def login(loginRequest: LoginRequest): Future[Option[User]] = {
    userRepository.getByLogin(loginRequest.id, loginRequest.password)
  }
}
