package controllers

import play.api._
import play.api.data._
import play.api.data.Forms._
import play.api.mvc._

object Application extends Controller {

  val stuffForm = Form(
    tuple(
      "stuff" -> text,
      "other" -> text
    )
  )
  
  def index = AuthAction { r =>
    implicit val id = r.identity
    Ok(views.html.index("Your new application is ready."))
  }
  
}