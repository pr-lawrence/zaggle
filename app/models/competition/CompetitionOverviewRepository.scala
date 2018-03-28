package models.competition

import java.sql.Date
import java.time.{LocalDateTime, LocalTime}
import javax.inject._

import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

/**
  * @author Lawrence
  * @since 2018. 2. 9.
  * @note
  * @version 0.1.0
  */
@Singleton
class CompetitionOverviewRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {

  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  private class CompetitionOverviewTable(tag: Tag) extends Table[CompetitionOverview](tag, "tb_competition_overview") {

    implicit val localDateToDate = MappedColumnType.base[LocalDateTime, Date](
      ldt => Date.valueOf(ldt.toLocalDate()),
      d => LocalDateTime.of(d.toLocalDate, LocalTime.MIDNIGHT)
    )

    def competitionOverviewId = column[Long]("competition_overview_id", O.PrimaryKey, O.AutoInc)

    def competitionId = column[Long]("competition_id")

    def description = column[String]("description")

    def evaluation = column[String]("evaluation")

    def prizes = column[String]("prizes")

    def kernelsFaq = column[String]("kernels_faq")

    def timeline = column[String]("timeline")

    def regiDate = column[LocalDateTime]("regi_date")

    /**
      * This is the tables default "projection".
      *
      * It defines how the columns are converted to and from the Person object.
      *
      * In this case, we are simply passing the id, name and page parameters to the Person case classes
      * apply and unapply methods.
      */

    def * = (competitionOverviewId, competitionId, description, evaluation, prizes, kernelsFaq, timeline, regiDate) <> ((CompetitionOverview.apply _).tupled, CompetitionOverview.unapply)
  }

  private val competitionOverview = TableQuery[CompetitionOverviewTable]

  def list(): Future[Seq[CompetitionOverview]] = db.run {
    competitionOverview.result
  }

  def getByCompetitionId(id: Long): Future[Seq[CompetitionOverview]] = db.run {
    competitionOverview.filter(_.competitionId === id).result
  }
}
