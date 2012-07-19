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

case class ContributeJournalist(@Key("_id") id: ObjectId = new ObjectId,
    identity: String,
    groups: List[JournalistGroup] = List()) extends Loggable

object ContributeJournalist extends Loggable {

  object Dao extends SalatDAO[ContributeJournalist, ObjectId](collection = MongoDataSource.journalistsCollection) with CasbahConverstionHelpers

  def apply(id: String): Option[ContributeJournalist] = {
    Dao.findOne(MongoDBObject("_id" -> new ObjectId(id)))
  }

}