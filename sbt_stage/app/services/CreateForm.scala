package services

import play.api.data._
import play.api._
import play.mvc._
import play.api.data.Forms._

object CreateForm {



  val createForm: Form[ContactData] = Form{
    mapping(
      "name" -> nonEmptyText,
      "phone" -> longNumber
    )(ContactData.apply)(ContactData.unapply)
  }
}

