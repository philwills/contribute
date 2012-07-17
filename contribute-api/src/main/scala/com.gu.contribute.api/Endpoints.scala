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
    GetContributor.slug -> GetContributor,
    GetContributorRequests.slug -> GetContributorRequests,
    GetRequest.slug -> GetRequest
  )
  def get(key: String) = all.get(key)
}

object GetContributor extends Endpoint {
  lazy val slug = "getContributor"
  lazy val path = "/contributor/*"
  lazy val stringFormat = "/contributor/%s"
  lazy val description = "Get a contributor"
  lazy override val requiredParams = List(UserIdParam)
  lazy val method = HttpGetMethod
}

object GetContributorRequests extends Endpoint {
  lazy val slug = "getContributorRequests"
  lazy val path = "/contributor/*/requests"
  lazy val stringFormat = "/contributor/%s/requests"
  lazy val description = "Get a contributor's requests"
  lazy override val requiredParams = List(UserIdParam)
  lazy val method = HttpGetMethod
}

object GetRequest extends Endpoint {
  lazy val slug = "getRequest"
  lazy val path = "/request/*"
  lazy val stringFormat = "/request/%s"
  lazy val description = "Get a request"
  lazy override val requiredParams = List(RequestIdParam)
  lazy val method = HttpGetMethod
}