package controllers

import net.liftweb.json.{NoTypeHints, Serialization}
import play.api.mvc._
import play.api.mvc.Results._

object Api {
  implicit val formats = Serialization.formats(NoTypeHints)

  def submissions = Action {
    Ok(Serialization.write(Scaffolding.submissions))
  }
}
