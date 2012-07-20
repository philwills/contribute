package controllers

import net.liftweb.json.{NoTypeHints, Serialization}
import play.api.mvc._
import play.api.mvc.Results._
import play.api.{Logger, Play}
import play.api.libs.json.JsValue
import com.mongodb.casbah.Imports._
import scala.collection.JavaConversions._
import collection.immutable.ListMap
import org.joda.time.DateTime

object Api {
  implicit val formats = Serialization.formats(NoTypeHints)

  def submissions = Action {
    Ok(Serialization.write(Scaffolding.submissions))
  }

  def responses = AuthAction {  r =>
    implicit val id = r.identity
    Ok(Serialization.write ({
      val responses: Iterator[CalloutResponse] = for {
        callout <- Callouts.forJournalist(id.get)
        responses <- Responses.toCallout(callout)
      } yield responses
      responses.toList
    }))
  }

  def updateSubmission = Action { req =>
    Ok(req.body.asJson.map { js: JsValue =>
      Logger.info(js.toString())
      js.toString()
    }.getOrElse{Logger.error(req.body.toString());req.body.toString()})
  }

  def users = Action {
    Ok(Serialization.write(Mongo.users.map{ x =>
      ListMap.empty ++ x.toMap
    }))
  }

  def response = Action { req =>
    req.body.asJson map { js: JsValue =>
      Responses.save(CalloutResponse(
        id = (js \ "id").asOpt[String] getOrElse new ObjectId().toString,
        user = (js \ "user").as[String],
        callout  = (js \ "callout").as[String],
        text = (js \ "text").as[String],
        status = (js \ "status" \ "value").asOpt[String].map { s =>
          SubmissionStatus(s, (js \ "status" \ "by" \ "openid").asOpt[JsValue] map { by =>
            Identity(
              openid = (js \ "status" \ "by" \ "openid").as[String],
              email = (js \ "status" \ "by" \ "email").as[String],
              firstName = (js \ "status" \ "by" \ "firstName").as[String],
              lastName = (js \ "status" \ "by" \ "lastName").as[String]
            )
          })
        } getOrElse New()
      ))
      Ok(Responses.count().toString)
    } getOrElse {
      InternalServerError
    }
  }

  def sendCallout(id: String) = Action { req =>
    Callouts.findOneById(id) map  { callout =>
      Callouts.save(callout.copy(sentOn = Some(DateTime.now)))
      Ok("Sent")
    } getOrElse InternalServerError
  }
}

