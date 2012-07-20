package com.gu.contribute.api.model

import scala.collection.mutable.{Map => MutableMap}
import com.gu.management.Loggable
import org.joda.time.DateTime

case class User(var id: String,
    var primaryEmailAddress: String,
    val publicFields: MutableMap[String, String],
    val privateFields: MutableMap[String, String],
    val statusFields: MutableMap[String, Boolean],
    val dates: MutableMap[String, DateTime]) extends Loggable {

  def privateField(name: String) = privateFields.get(name)

  def publicField(name: String) = publicFields.get(name)

  def dateField(name: String) = dates.get(name)

  def status(name: String) = statusFields.get(name)

}

object User extends Loggable {

  def apply(id: String) = {
    new User(id, "test@example.com", MutableMap(), MutableMap(), MutableMap(), MutableMap()) //todo get user from identity here...
  }

}