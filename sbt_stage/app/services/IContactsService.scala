package services

import scala.concurrent.Future

trait IContactsService[A] {
  def create(contact: ContactData): Future[A]

  def update(id: String, name: String, number: Long): Future[A]

  def delete(id: String): Future[Option[A]]

  def lookup(id: String): Future[Option[A]]

  def getAll: Future[Iterable[A]]

  def findBySubstring(nameSubstring: String, phoneSubstring: String): Future[Seq[A]]
}
