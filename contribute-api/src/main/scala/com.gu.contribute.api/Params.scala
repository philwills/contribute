package com.gu.contribute.api

abstract class Param {
  val name: String
  val description: String
}

object UserIdParam extends Param {
  lazy val name = "userId"
  lazy val description = "The user ID"
}

object RequestIdParam extends Param {
  lazy val name = "requestId"
  lazy val description = "The request ID"
}