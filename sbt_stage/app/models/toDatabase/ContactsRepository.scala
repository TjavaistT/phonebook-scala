package models.toDatabase

import javax.inject.Singleton
import models.entities.Contact
import play.api.{Logger, MarkerContext}
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

@Singleton
class ContactsRepository extends IContactsRepository[Contact] {

  private val logger = Logger(this.getClass)

  val db_ = Database.forConfig("phones_db")
  val phones = TableQuery[ContactsTable]
  val setup = DBIO.seq(
    phones.schema.createIfNotExists
  )

  val operationTimeout: FiniteDuration = 5.seconds

  {
    Await.result(db_.run(setup), operationTimeout)
  }

  override def list()(
    implicit mc: MarkerContext): Future[Seq[Contact]] = {
    Future {
      logger.trace(s"listing storage: ")
      Await.result(db_.run(phones.result),operationTimeout)
    }
  }

  override def get(id: String)(
    implicit mc: MarkerContext): Future[Option[Contact]] = {
    Future {
      logger.trace(s"get: id = $id")
      val query = phones.filter(_.id === id)
      Await.result(db_.run(query.result), operationTimeout).headOption
    }
  }

  override def createOrUpdate(data: Contact)(implicit mc: MarkerContext): Future[String] = {
    Future {
      logger.trace(s"insert or update: data = $data")
      val q = phones.insertOrUpdate(data)
      Await.result(db_.run(q), operationTimeout)
      data.id
    }
  }

  override def delete(id: String)(implicit mc: MarkerContext): Future[String] = {
    Future {
      logger.trace(s"delete: id = $id")
      val q = phones.filter(_.id === id)
      val a = q.delete
      Option(Await.result(db_.run(a), operationTimeout))
      id
    }
  }
}