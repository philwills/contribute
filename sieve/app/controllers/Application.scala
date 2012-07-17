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
    Ok(views.html.index(Scaffolding.submissions))
  }
}

case class Submission(text: String, status: SubmissionStatus)

case class SubmissionStatus(value: String, by: Option[Identity])
object New  { def apply() = SubmissionStatus("new", None) }
object ReviewedNoAction { def apply(by: Identity) = SubmissionStatus("no-action", Some(by)) }
object FollowingUp { def apply(by: Identity) = SubmissionStatus("following-up", Some(by)) }

object Scaffolding {
  def submissions = List(
    Submission("Someone at my hamster", New()),
    Submission("No-one ate my hamster", ReviewedNoAction(phil)),
    Submission("The Queen ate my hamster", FollowingUp(phil))
  )

  lazy val phil = Identity("", "phil.wills@guardian.co.uk", "Phil", "Wills")
}