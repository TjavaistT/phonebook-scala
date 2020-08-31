package controllers

object CreateForm {
  import play.api.data.Form
  import play.api.data.Forms._

  case class CreateData(name: String, phone: Long)

  val createForm: Form[CreateData] = Form{
    mapping(
      "name" -> nonEmptyText,
      "phone" -> longNumber
    )(CreateData.apply)(CreateData.unapply)
  }
}
