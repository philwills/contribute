package com.gu.contribute.api.model

import com.gu.management.Loggable
import org.joda.time.DateTime

case class ContributorExpertise(what: String,
    where: Option[String],
    from: Option[DateTime],
    description: String = "",
    to: Option[DateTime],
    `type`: String = "professional") extends Loggable

object ContributorExpertise extends Loggable {

}