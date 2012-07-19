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

  def responses = AuthAction { r =>
    implicit val id = r.identity
    Ok(views.html.index(Scaffolding.submissions))
  }

  def callouts = AuthAction { r =>
    implicit val id = r.identity
    Ok(views.html.callouts(Scaffolding.submissions))
  }
  
  def index = AuthAction { r =>
    implicit val id = r.identity
    Ok(views.html.index(Scaffolding.submissions))
  }
}

case class Submission(id: String, text: String, status: SubmissionStatus)

case class SubmissionStatus(value: String, by: Option[Identity])
object New  { def apply() = SubmissionStatus("new", None) }
object ReviewedNoAction { def apply(by: Identity) = SubmissionStatus("no-action", Some(by)) }
object FollowingUp { def apply(by: Identity) = SubmissionStatus("following-up", Some(by)) }

object Scaffolding {
  def submissions = List(
    Submission("1", "Someone at my hamster", New()),
    Submission("2", "No-one ate my hamster", ReviewedNoAction(phil)),
    Submission("3", "The Queen ate my hamster", FollowingUp(phil))
  )

  lazy val phil = Identity("", "phil.wills@guardian.co.uk", "Phil", "Wills")
}