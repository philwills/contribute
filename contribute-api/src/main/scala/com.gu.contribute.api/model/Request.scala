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

case class Request(@Key("_id") id: ObjectId = new ObjectId,
    title: String,
    description: String,
    imageUri: Option[String],
    startDate: DateTime = new DateTime,
    endDate: Option[DateTime],
    journalist: ObjectId,
    contributors: List[ObjectId] = List()) extends Loggable {

  def upsert = Request.upsert(this)

}

object Request extends Loggable {

  object Dao extends SalatDAO[Request, ObjectId](collection = MongoDataSource.requestsCollections) with CasbahConverstionHelpers

  def apply(id: String): Option[Request] = {
    Dao.findOne(MongoDBObject("_id" -> new ObjectId(id)))
  }

  def apply(title: String, description: String, imageUri: Option[String], endDate: Option[DateTime], journalistId: String, contributorIds: List[String]): Request = {
    val contributors = contributorIds.map(new ObjectId(_))
    Request(title = title, description = description, imageUri = imageUri, endDate = endDate, journalist = new ObjectId(journalistId), contributors = contributors)
  }

  def getForContributor(userId: String): List[Request] = {
    Dao.find(MongoDBObject("contributors" -> new ObjectId(userId))).toList
  }

  def upsert(request: Request): Option[Request] = {
    Dao.collection.findAndModify(Map("_id" -> request.id), DBObject(), DBObject(), false, request, true, true) map(grater[Request].asObject(_))
  }

}