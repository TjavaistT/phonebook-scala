package controllers

import java.util.UUID.randomUUID

import controllers.RestContactsController.createForm
import javax.inject.Inject
import models.entities.Contact
import play.api.data.Form
import play.api.libs.json.Json
import play.api.mvc._
import services._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._

class RestContactsController @Inject()(contactsService: ContactsService,
                                         cc: ControllerComponents)
                                    extends AbstractController(cc)
{
  implicit val contactFormat = Json.format[Contact]

  def getAll = Action.async { implicit request: Request[AnyContent]  =>
    contactsService.getAll.map { posts =>
      Ok(Json.toJson(posts))
    }
  }

  def get(id: String)= Action.async {
    implicit request =>
      contactsService.lookup(id).map { post =>
        Ok(Json.toJson(post))
      }
  }

  def add = Action.async { implicit request:Request[AnyContent] =>
    createForm.bindFromRequest().fold(
      errorForm => {
        errorForm.errors.foreach(println)
        Future.successful(BadRequest("Error in create form!"))
      },
      dataContact => {

        contactsService.create(dataContact).map { resultContact =>
            Created(Json.toJson(resultContact))
          }
      }
    )
  }

  def update(id: String) = Action.async { implicit request: Request[AnyContent] =>

    createForm.bindFromRequest().fold(
      errorForm => {
        errorForm.errors.foreach(println)
        Future.successful(BadRequest("Error in update form!"))
      },
      contactData => {
        contactsService.update(id, contactData.name, contactData.phoneNumber).map { result =>
          Created(Json.toJson(result))
        }
      }
    )
  }

  def delete(id: String)= Action.async {
    implicit request =>
      contactsService.delete(id).map { post =>
        Ok(Json.toJson(post))
      }
  }

  def showFiltered: Action[AnyContent] = Action.async {
    implicit request =>
      val nameSubstring = request.getQueryString("nameSubstring").getOrElse(default = "")
      val phoneSubstring = request.getQueryString("phoneSubstring").getOrElse(default = "")
      contactsService.findBySubstring(nameSubstring, phoneSubstring).map { posts =>
        Ok(Json.toJson(posts))
      }
  }
}

object RestContactsController{

  val createForm: Form[ContactForm] = {
    import play.api.data.Forms._

    Form(
      mapping(
        "name" -> nonEmptyText,
        "phoneNumber" -> longNumber
      )(ContactForm.apply)(ContactForm.unapply)
    )
  }
}