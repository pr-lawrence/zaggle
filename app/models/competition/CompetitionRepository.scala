package models.competition

import java.time.LocalDateTime
import javax.inject._

/**
  *
  * @author: Lawrence
  * @since: 2018. 2. 4.
  * @note:
  */
@Singleton
class CompetitionRepository {

  val mock =
    Competition("1", "" ,"", "Mercari Price Suggestion Challenge", "Can you automatically suggest product prices to online sellers?", 1L, 2323L, 3000000L, LocalDateTime.now) ::
      Competition("2", "" ,"", "Toxic Comment Classification Challenge", "Identify and classify toxic online comments", 1L, 2323L, 3000000L, LocalDateTime.now) ::
      Competition("3", "" ,"", "IEEE's Signal Processing Society - Camera Model Identification", "Identify from which camera an image was taken\n$25,000\nPrize Money", 1L, 2323L, 3000000L, LocalDateTime.now) :: Nil

  def select(): List[Competition] = mock

  def findById(id: String): Option[Competition] = {
    mock.find(_.competId == id)
  }
}