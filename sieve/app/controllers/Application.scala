package controllers

import play.api._
import libs.ws.WS
import play.api.data._
import play.api.data.Forms._
import play.api.mvc._
import com.novus.salat._
import json.TimestampDateStrategy
import org.scala_tools.time.StaticDateTimeZone
import org.joda.time.DateTime

object Application extends Controller {

  val stuffForm = Form(
    tuple(
      "stuff" -> text,
      "other" -> text
    )
  )

  def responses = AuthAction { r =>
    implicit val id = r.identity
    Ok(views.html.index {
      for {
        callout <- Callouts.forJournalist(id.get)
        responses <- Responses.toCallout(callout)
      } yield responses
    })
  }

  val calloutForm = Form(
    mapping(
      "title" -> text,
      "description" -> text
   )((title, description) => Callout(title, description))
    ((callout: Callout) => Some(( callout.title, callout.description))))

  def callouts = AuthAction { implicit r =>
    implicit val id = r.identity
    Ok(views.html.callouts(
      Callouts.forJournalist(id.get).toSeq,
      calloutForm
    ))
  }

  def callout = AuthAction { implicit r =>
    implicit val id = r.identity
    val partialCallout = calloutForm.bindFromRequest.get
    Callouts.save(partialCallout.copy(journalist = id.get.openid))
    Redirect(routes.Application.callouts)
  }
  
  def index = AuthAction { r =>
    Redirect(routes.Application.responses)
  }

  def users = AuthAction { implicit r =>
    implicit val id = r.identity
    Async {
      WS.url("http://ec2-46-137-10-183.eu-west-1.compute.amazonaws.com:8080/contributors").get map { result =>
        implicit val ctx = context.ctx
        val userJson = (result.json \"content").toString
        val users = grater[User].fromJSONArray(userJson)
        Ok(views.html.users(users))
      }
    }
  }
}

case class User(id: String, identity: String, email: String, expertise: List[Expertise])
case class Expertise(what: String, where: Option[String], from: DateTime, description: String)

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

import com.novus.salat.{TypeHintFrequency, StringTypeHintStrategy, Context}
import com.novus.salat.json.{StringDateStrategy, JSONConfig}
import org.joda.time.format.ISODateTimeFormat
import org.joda.time.DateTimeZone

package object context {
  import org.scala_tools.time.Imports._

  implicit val ctx = new Context {
    val name = "json-test-context"
    override val typeHintStrategy = StringTypeHintStrategy(when = TypeHintFrequency.WhenNecessary,
      typeHint = "_t")
    override val jsonConfig = JSONConfig(dateStrategy =
      TimestampDateStrategy(StaticDateTimeZone.forID("Europe/London")))
  }
}