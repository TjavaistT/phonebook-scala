package services

import play.api.data._
import play.api._
import play.mvc._
import play.api.data.Forms._

object SearchForm {

  val searchForm: Form[ContactData] = Form {
    mapping(
      "nameSubstring" -> text,
      "phoneSubstring" -> longNumber
    )(ContactData.apply)(ContactData.unapply)
  }
}

