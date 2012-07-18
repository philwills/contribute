package com.gu.contribute.api.model

import com.novus.salat.annotations._
import org.bson.types.ObjectId
import com.novus.salat.dao.SalatDAO
import com.gu.contribute.api.mongo.MongoDataSource
import com.gu.contribute.api.CasbahConverstionHelpers
import com.novus.salat._
import com.novus.salat.global._
import com.mongodb.casbah.Imports._
import com.gu.contribute.api.SalatTypeConversions._
import com.gu.management.Loggable
import com.mongodb.casbah.commons.MongoDBObject

case class Journalist(@Key("_id") id: ObjectId = new ObjectId,
    email: String,
    groups: List[Map[String, List[ObjectId]]] = List()) extends Loggable

object Journalist extends Loggable {

  object Dao extends SalatDAO[Journalist, ObjectId](collection = MongoDataSource.journalistsCollection) with CasbahConverstionHelpers

  def apply(id: String): Option[Journalist] = {
    Dao.findOne(MongoDBObject("_id" -> new ObjectId(id)))
  }

}