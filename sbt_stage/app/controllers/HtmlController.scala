package controllers

import services._
import services.CreateForm.createForm
import services.SearchForm.searchForm
import javax.inject.Inject
import models.entities.Contact
import play.api.data.Form
import play.api.mvc._
import scala.language.postfixOps

import scala.concurrent.{CanAwait, ExecutionContext}
import scala.concurrent.ExecutionContext.global
import scala.concurrent.duration.DurationInt
import scala.util.{Failure, Success}


class HtmlController @Inject()(contactService: ContactsService,
                               cComponents: MessagesControllerComponents
                              ) (implicit ec: ExecutionContext
                              ) extends MessagesAbstractController(cComponents)
{
  private val createUrl = routes.HtmlController.addContact()

  def index = Action.async { implicit request: MessagesRequest[AnyContent] =>

    val nameSubstr = request.getQueryString("nameSubstring").getOrElse("")
    val phoneSubstr = request.getQueryString("phoneSubstring").getOrElse("")

    val maybeContacts = contactService.findBySubstring(nameSubstr, phoneSubstr)

    maybeContacts.map(contacts => Ok(views.html.index(contacts, createForm, searchForm, createUrl)))
  }

  def addContact: Action[AnyContent] = Action { implicit request =>

    val errorFunction = { formWithError: Form[ContactData] =>
      BadRequest(views.html.index(Nil, formWithError, searchForm, createUrl))
    }

    val successFunction = { data: ContactData =>
      contactService.create(ContactData(data.name, data.phone))
      Redirect(routes.HtmlController.index())
    }

    createForm.bindFromRequest.fold(errorFunction, successFunction)
  }

  def editContact(contact_id: String) = Action { implicit request =>
    val errorFunction = {formWithError: Form[ContactData] =>
      BadRequest("Error in Edit Form!")
    }

    val successFunction = { data: ContactData =>
      contactService.update(contact_id, data.name, data.phone)
      Redirect(routes.HtmlController.index()).flashing("info" -> "contact update")
    }

    createForm.bindFromRequest.fold(errorFunction, successFunction)
  }

  def deleteContact(contact_id: String) = Action {

    contactService.delete(contact_id)

    Redirect(routes.HtmlController.index()).flashing("info" -> "contact deleted")
  }
}
