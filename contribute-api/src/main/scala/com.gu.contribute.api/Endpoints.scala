package com.gu.contribute.api

abstract class Endpoint {
  val slug: String
  val path: String
  val stringFormat: String
  lazy val requiredParams: List[Param] = List()
  lazy val optionalParams: List[Param] = List()
  val description: String
  val method: HttpMethod
}

object Endpoint {
  lazy val all = Map(
    GetUser.slug -> GetUser,
    SearchUsers.slug -> SearchUsers
  )
  def get(key: String) = all.get(key)
}

object GetUser extends Endpoint {
  lazy val slug = "getUser"
  lazy val path = "/user/*"
  lazy val stringFormat = "/user/%s"
  lazy val description = "Get a user"
  lazy override val requiredParams = List(UserIdParam)
  lazy val method = HttpGetMethod
}

object SearchUsers extends Endpoint {
  lazy val slug = "searchUsers"
  lazy val path = "/search"
  lazy val stringFormat = "/search"
  lazy val description = "Search users"
  lazy val method = HttpPostMethod
}