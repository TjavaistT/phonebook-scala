package controllers

object SearchForm {
  import play.api.data.Form
  import play.api.data.Forms._

  case class SearchData(nameSubstring: String, phoneSubstring: Long)

  val searchForm: Form[SearchData] = Form{
    mapping(
      "nameSubstring" -> text,
      "phoneSubstring" -> longNumber
    )(SearchData.apply)(SearchData.unapply)
  }
}
