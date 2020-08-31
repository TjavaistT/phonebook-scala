package models.toDatabase

import models.entities.Contact
import slick.jdbc.PostgresProfile.api._

class ContactsTable(tag: Tag) extends Table[Contact](tag, "phonebook") {
  def id = column[String]("ID", O.PrimaryKey)

  def name = column[String]("NAME")

  def number = column[Long]("NUMBER")

  def * = (id, name, number) <> ((Contact.apply _).tupled, Contact.unapply)
}
