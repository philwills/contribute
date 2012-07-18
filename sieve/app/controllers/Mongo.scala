package controllers

import com.mongodb.casbah.Imports._
import com.mongodb.casbah.MongoURI

object Mongo {
  val uri = MongoURI("mongodb://sieveapp:colander@flame.mongohq.com:27040/sieve")

  val con = MongoConnection(uri)

  val db = {
    val nonAuthDB = con("sieve")
    nonAuthDB.authenticate(uri.username.get, new String(uri.password.get))
    nonAuthDB
  }

  val users = db("users")
}
