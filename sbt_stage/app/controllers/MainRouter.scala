package controllers

import javax.inject.Inject
import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._


class MainRouter @Inject()(htmlController: HtmlController, restController: RestContactsController) extends SimpleRouter {

  override def routes: Routes = {

    case POST(p"/") => htmlController.addContact

    case POST(p"/$contact_id") => htmlController.editContact(contact_id)

    case GET(p"/delete/$contact_id") => htmlController.deleteContact(contact_id)

    case GET(p"/phones") => restController.getAll

    case GET(p"/phones/searchBySubstr") => restController.showFiltered

    case POST(p"/phones/createNewPhone") => restController.add

    case GET(p"/phone/$id") => restController.get(id)

    case POST(p"/phone/$id") => restController.update(id)

    case DELETE(p"/phone/$id") => restController.delete(id)

  }
}