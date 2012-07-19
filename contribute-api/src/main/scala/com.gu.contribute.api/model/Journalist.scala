package com.gu.contribute.api.model

import org.bson.types.ObjectId
import com.gu.management.Loggable
import org.joda.time.DateTime

case class Journalist(id: ObjectId = new ObjectId,
    identity: String,
    email: String,
    firstName: Option[String],
    lastName: Option[String],
    gender: Option[String],
    dateOfBirth: Option[DateTime],
    country: Option[String],
    groups: List[JournalistGroup] = List()) extends Loggable

object Journalist extends Loggable {

  def apply(id: String): Option[Journalist] = {
    val contributeJournalist = ContributeJournalist(id).get
    val user = User(contributeJournalist.identity)
    val Journalist = contributeJournalist2Journalist(contributeJournalist, user)
    Some(Journalist)
  }

  def contributeJournalist2Journalist(contributeJournalist: ContributeJournalist, user: User) = {
    val identity = user.id
    val email = user.primaryEmailAddress
    val firstName = user.privateFields.get("firstName")
    val lastName = user.privateFields.get("secondName")
    val gender = user.privateFields.get("gender")
    val dateOfBirth = user.dates.get("dateOfBirth")
    val country = user.privateFields.get("country")
    Journalist(contributeJournalist.id, identity, email, firstName, lastName, gender, dateOfBirth, country, contributeJournalist.groups)
  }

}