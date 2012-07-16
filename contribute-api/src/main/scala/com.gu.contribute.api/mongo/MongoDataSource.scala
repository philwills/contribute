package com.gu.contribute.api.mongo

import com.mongodb.casbah.{MongoURI, MongoDB, MongoConnection}
import com.gu.contribute.api.ConfigurationManager._
import com.mongodb.{ServerAddress}
import com.gu.management.Loggable
import com.gu.management.mongodb.MongoManagement

object MongoDataSource extends MongoManagement with Loggable {

  val (connection, db) = mongoDbUriOption match {
    case Some(uri) => {
      val mongoUri = MongoURI(uri)
      val connection: MongoConnection = MongoConnection(mongoUri)
      wireInTimingMetric(connection)
      val db: MongoDB = MongoDB(connection, mongoUri.database)
      (connection, db)
    }
    case None => {
      val host = mongoDbHost get
      val connection = MongoConnection(host.split(",").toList.map(server => new ServerAddress(server)))
      wireInTimingMetric(connection)
      val db = connection(mongoDbName)
      (connection, db)
    }
  }

  if(!db.authenticate(mongoDbUsername, mongoDbPassword)) {
    throw new Exception("Authentication failed")
  }

  lazy val usersCollection = createCollection("users")
  lazy val messagesCollection = createCollection("messages")

  private def createCollection(name: String) = {
    val collection = db(name)
    if(mongoInClusterMode) {
      collection.slaveOk()
    }
    collection
  }

}