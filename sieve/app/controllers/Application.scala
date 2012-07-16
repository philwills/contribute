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
    Ok(views.html.index(List(
      Submission("Someone at my hamster", New),
      Submission("No-one ate my hamster", ReviewedNoAction(phil)),
      Submission("The Queen ate my hamster", FollowingUp(phil))
    )))
  }

  lazy val phil = Identity("", "phil.wills@guardian.co.uk", "Phil", "Wills")
}

case class Submission(text: String, status: SubmissionStatus)

sealed trait SubmissionStatus
case object New extends SubmissionStatus
case class ReviewedNoAction(by: Identity) extends SubmissionStatus
case class FollowingUp(by: Identity) extends SubmissionStatus