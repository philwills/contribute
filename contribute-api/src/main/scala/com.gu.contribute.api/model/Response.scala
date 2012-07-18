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

case class Response(@Key("_id") id: ObjectId = new ObjectId,
    contributor: ObjectId,
    request: ObjectId,
    text: String = "",
    imageUri: Option[String],
    date: DateTime = new DateTime,
    endorsed: Boolean = false) extends Loggable {

  def upsert = Response.upsert(this)

}

object Response extends Loggable {

  object Dao extends SalatDAO[Response, ObjectId](collection = MongoDataSource.responsesCollection) with CasbahConverstionHelpers

  def apply(id: String): Option[Response] = {
    Dao.findOne(MongoDBObject("_id" -> new ObjectId(id)))
  }

  def apply(contributorId: String, requestId: String, text: String, imageUri: Option[String]): Response = {
    Response(contributor = new ObjectId(contributorId), request = new ObjectId(requestId), text = text, imageUri = imageUri)
  }

  def getForRequest(requestId: String): List[Response] = {
    Dao.find(MongoDBObject("request" -> new ObjectId(requestId))).toList
  }

  def upsert(response: Response): Option[Response] = {
    Dao.collection.findAndModify(Map("_id" -> response.id), DBObject(), DBObject(), false, response, true, true) map(grater[Response].asObject(_))
  }

}