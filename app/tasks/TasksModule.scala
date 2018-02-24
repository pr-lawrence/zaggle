package tasks


import play.api.inject.{SimpleModule, _}

/**
  *
  * @author Lawrence
  * @since 2018. 2. 24.
  * @note
  * @version 0.1.1
  */
class TasksModule extends SimpleModule(bind[BithumbTask].toSelf.eagerly()){
}
