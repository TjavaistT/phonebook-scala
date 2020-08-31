package controllers

import javax.inject.Inject
import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._


class MainRouter @Inject()(htmlController: HtmlController, restController: RestContactsController) extends SimpleRouter {

  override def routes: Routes = {
    case GET(p"/") => htmlController.index

    case GET(p"/phones") => restController.getAll

    case GET(p"/phones/searchBySubstr") => restController.showFiltered()

    case POST(p"/phones/createNewPhone") => restController.add

    case GET(p"/phone/$id") => restController.get(id)

    case POST(p"/phone/$id") => restController.update(id)

    case DELETE(p"/phone/$id") => restController.delete(id)

  }
}