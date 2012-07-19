package controllers

import com.mongodb.casbah.Imports._
import com.mongodb.casbah.MongoURI
import com.novus.salat._
import com.novus.salat.annotations._
import com.novus.salat.global._
import dao.SalatDAO
import com.novus.salat.annotations._
import com.mongodb.casbah.commons.conversions.scala._
import org.joda.time.DateTime


object Mongo {
  RegisterJodaTimeConversionHelpers()

  val uri = MongoURI("mongodb://sieveapp:colander@flame.mongohq.com:27040/sieve")

  val con = MongoConnection(uri)

  val db = {
    val nonAuthDB = con("sieve")
    nonAuthDB.authenticate(uri.username.get, new String(uri.password.get))
    nonAuthDB
  }

  val users = db("users")

  val callouts = db("callouts")

  val responses = db("responses")

  val journalists = db("journalists")
}

object Journalists extends SalatDAO[Identity, String] (
  collection = Mongo.journalists
)
object Callouts extends SalatDAO[Callout, String] (
  collection = Mongo.callouts
) {

  def forJournalist(journo: Identity) = {
    find(MongoDBObject("journalist" -> journo.openid))
  }
}
object Responses extends SalatDAO[Response, String] (
  collection = Mongo.responses
)

case class Callout(
                    @Key("_id") id: String = new ObjectId().toString,
                    journalist: String,
                    title: String,
                    description: String,
                    users: List[String] = List(),
                    createdOn: DateTime = DateTime.now())

object Callout{
  def apply(title: String, description: String): Callout = Callout(
    new ObjectId().toString,
    "",
    title,
    description,
    List(),
    DateTime.now()
  )
}

case class Response(
                     @Key("_id") id: String = new ObjectId().toString,
                     user: String,
                     callout: String,
                     text: String)
