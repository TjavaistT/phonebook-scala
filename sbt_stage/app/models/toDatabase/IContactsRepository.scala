package models.toDatabase

import play.api.MarkerContext

import scala.concurrent.Future

trait IContactsRepository[A] {
  def createOrUpdate(data: A)(implicit mc: MarkerContext): Future[String]

  def list()(implicit mc: MarkerContext): Future[Seq[A]]

  def delete(id: String)(implicit mc: MarkerContext): Future[String]

  def get(id: String)(implicit mc: MarkerContext): Future[Option[A]]
}
