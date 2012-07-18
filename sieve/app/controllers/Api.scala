package controllers

import net.liftweb.json.{NoTypeHints, Serialization}
import play.api.mvc._
import play.api.mvc.Results._
import play.api.{Logger, Play}
import play.api.libs.json.JsValue

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
    Ok(Serialization.write(Mongo.users.toList))
  }
}

