package com.gu.contribute.api.model

import org.bson.types.ObjectId
import com.gu.management.Loggable
import org.joda.time.DateTime

case class Contributor(id: ObjectId = new ObjectId,
    identity: String,
    email: String,
    firstName: Option[String],
    lastName: Option[String],
    gender: Option[String],
    dateOfBirth: Option[DateTime],
    country: Option[String],
    expertise: List[ContributorExpertise],
    notes: List[ContributorNote] = List()) extends Loggable

object Contributor extends Loggable {

  def apply(id: String): Option[Contributor] = {
    val contributeContributor = ContributeContributor(id).get
    val user = User(contributeContributor.identity)
    val contributor = contributeContributor2Contributor(contributeContributor, user)
    Some(contributor)
  }

  def retrieveAll(): List[Contributor] = {
    ContributeContributor.retrieveAll().map(contributeContributor => contributeContributor2Contributor(contributeContributor, User(contributeContributor.identity)))
  }

  def contributeContributor2Contributor(contributeContributor: ContributeContributor, user: User) = {
    val identity = user.id
    val email = user.primaryEmailAddress
    val firstName = user.privateFields.get("firstName")
    val lastName = user.privateFields.get("secondName")
    val gender = user.privateFields.get("gender")
    val dateOfBirth = user.dates.get("dateOfBirth")
    val country = user.privateFields.get("country")
    Contributor(contributeContributor.id, identity, email, firstName, lastName, gender, dateOfBirth, country, contributeContributor.expertise, contributeContributor.notes)
  }

}