package com.gu.contribute.api

abstract class Param {
  val name: String
  val description: String
}

object IdentityIdParam extends Param {
  lazy val name = "id"
  lazy val description = "An identity ID."
}

object RequestTitleParam extends Param {
  lazy val name = "title"
  lazy val description = "The title of the contribution request."
}

object RequestDescriptionParam extends Param {
  lazy val name = "description"
  lazy val description = "A description of the contribution request."
}

object RequestContributorParam extends Param {
  lazy val name = "contributor"
  lazy val description = "The ID of a contributor included within this request. Can be specified multiple times."
}

object RequestImageUriParam extends Param {
  lazy val name = "imageUri"
  lazy val description = "The URI of an image related to this contribution request."
}

object RequestEndDateParam extends Param {
  lazy val name = "endDate"
  lazy val description = "The date this contribution request ends. The request is ongoing if this is not specified."
}

object ResponseTextParam extends Param {
  lazy val name = "text"
  lazy val description = "The text of this response."
}

object ResponseImageUriParam extends Param {
  lazy val name = "imageUri"
  lazy val description = "The URI of an image related to this response."
}