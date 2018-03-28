package models.competition

import java.sql.Date
import java.time.{LocalDateTime, LocalTime}
import javax.inject.{Inject, Singleton}

import play.api.db.slick._
import slick.jdbc.JdbcProfile

import scala.concurrent._

/**
  * @author Lawrence
  * @since 2018. 2. 4.
  * @note
  * @version 0.1.0
  */
@Singleton
class CompetitionRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {

  // We want the JdbcProfile for this provider
  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  // These imports are important, the first one brings db into scope, which will let you do the actual db operations.
  // The second one brings the Slick DSL into scope, which lets you define the table and other queries.
  import dbConfig._
  import profile.api._

  /**
    * Here we define the table. It will have a name of people
    */
  private class CompetitionTable(tag: Tag) extends Table[Competition](tag, "tb_competition") {

    implicit val localDateToDate = MappedColumnType.base[LocalDateTime, Date](
      ldt => Date.valueOf(ldt.toLocalDate()),
      d => LocalDateTime.of(d.toLocalDate, LocalTime.MIDNIGHT)
    )
    /** The ID column, which is the primary key, and auto incremented */
    //    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    /** The name column */
    //    def name = column[String]("name")

    /** The age column */
    //    def age = column[Int]("age")

    def competId = column[Long]("compet_id", O.PrimaryKey, O.AutoInc)

    def examinerId = column[String]("examiner_id")

    def headline = column[String]("headline")

    def description = column[String]("description")

    def imgUrl = column[String]("img_url")

    def stateTp = column[Long]("state_tp")

    def participantCnt = column[Long]("participant_cnt")

    def reward = column[Long]("reward")

    def regiDate = column[LocalDateTime]("regi_date")

    /**
      * This is the tables default "projection".
      *
      * It defines how the columns are converted to and from the Person object.
      *
      * In this case, we are simply passing the id, name and page parameters to the Person case classes
      * apply and unapply methods.
      */

    def * = (competId, examinerId, headline, description, imgUrl, stateTp, participantCnt, reward, regiDate) <> ((Competition.apply _).tupled, Competition.unapply)
  }

  /**
    * The starting point for all queries on the people table.
    */
  private val competition = TableQuery[CompetitionTable]

  /**
    * Create a person with the given name and age.
    *
    * This is an asynchronous operation, it will return a future of the created person, which can be used to obtain the
    * id for that person.
    */
  //  def create(name: String, age: Int): Future[Person] = db.run {
  //    // We create a projection of just the name and age columns, since we're not inserting a value for the id column
  //    (people.map(p => (p.name, p.age))
  //      // Now define it to return the id, because we want to know what id was generated for the person
  //      returning people.map(_.id)
  //      // And we define a transformation for the returned value, which combines our original parameters with the
  //      // returned id
  //      into ((nameAge, id) => Person(id, nameAge._1, nameAge._2))
  //      // And finally, insert the person into the database
  //      ) += (name, age)
  //  }

  /**
    * List all the people in the database.
    */
  def list(): Future[Seq[Competition]] = db.run {
    competition.result
  }

  def get(id: Long): Future[Seq[Competition]] = db.run {
    competition.filter(_.competId === id).result
  }

}