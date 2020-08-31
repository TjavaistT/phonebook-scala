package models.entities

import play.api.libs.json._

final case class Contact(id: String, name: String, number: Long)

object Contact {
  implicit val format: Format[Contact] = Json.format
}