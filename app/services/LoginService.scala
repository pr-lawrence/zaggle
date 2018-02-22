package services

import javax.inject.{Inject, Singleton}

import common.CryptoUtils
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
    val hashedPassword: String = CryptoUtils.sha256Hash(loginRequest.password)
    userRepository.getByLogin(loginRequest.id, hashedPassword)
  }
}