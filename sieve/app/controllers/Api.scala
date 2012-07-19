package controllers

import net.liftweb.json.{NoTypeHints, Serialization}
import play.api.mvc._
import play.api.mvc.Results._
import play.api.{Logger, Play}
import play.api.libs.json.JsValue
import com.mongodb.casbah.Imports._
import scala.collection.JavaConversions._
import collection.immutable.ListMap

object Api {
  implicit val formats = Serialization.formats(NoTypeHints)

  def submissions = Action {
    Ok(Serialization.write(Scaffolding.submissions))
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
      Ok("")
    } getOrElse (InternalServerError)
  }
}

