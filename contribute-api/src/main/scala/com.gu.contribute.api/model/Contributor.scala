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
import org.joda.time.DateTime

case class Contributor(@Key("_id") id: ObjectId = new ObjectId,
    email: String,
    expertise: List[ContributorExpertise],
    notes: List[ContributorNote] = List()) extends Loggable

object Contributor extends Loggable {

  object Dao extends SalatDAO[Contributor, ObjectId](collection = MongoDataSource.contributorsCollection) with CasbahConverstionHelpers

  def apply(id: String): Option[Contributor] = {
    Dao.findOne(MongoDBObject("_id" -> new ObjectId(id)))
  }

}