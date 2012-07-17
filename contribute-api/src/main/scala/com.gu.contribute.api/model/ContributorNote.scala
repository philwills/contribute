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

case class ContributorNote(@Key("_id") id: ObjectId = new ObjectId,
    text: String,
    from: Contributor) extends Loggable

object ContributorNote extends Loggable {

}