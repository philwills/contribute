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
    GetJournalist.slug -> GetJournalist,
    GetContributor.slug -> GetContributor,
    GetContributorRequests.slug -> GetContributorRequests,
    GetRequest.slug -> GetRequest,
    GetRequestResponses.slug -> GetRequestResponses,
    AddRequest.slug -> AddRequest,
    Respond.slug -> Respond,
    GetResponse.slug -> GetResponse
  )
  def get(key: String) = all.get(key)
}

object GetJournalist extends Endpoint {
  lazy val slug = "getJournalist"
  lazy val path = "/journalist/:userId"
  lazy val stringFormat = "/journalist/%s"
  lazy val description = "Get a journalist"
  lazy val method = HttpGetMethod
}

object GetContributor extends Endpoint {
  lazy val slug = "getContributor"
  lazy val path = "/contributor/:userId"
  lazy val stringFormat = "/contributor/%s"
  lazy val description = "Get a contributor"
  lazy val method = HttpGetMethod
}

object GetContributorRequests extends Endpoint {
  lazy val slug = "getContributorRequests"
  lazy val path = "/contributor/:userId/requests"
  lazy val stringFormat = "/contributor/%s/requests"
  lazy val description = "Get a contributor's requests"
  lazy val method = HttpGetMethod
}

object GetRequest extends Endpoint {
  lazy val slug = "getRequest"
  lazy val path = "/request/:requestId"
  lazy val stringFormat = "/request/%s"
  lazy val description = "Get a request"
  lazy val method = HttpGetMethod
}

object GetRequestResponses extends Endpoint {
  lazy val slug = "getRequestResponses"
  lazy val path = "/request/:requestId/responses"
  lazy val stringFormat = "/request/%s/responses"
  lazy val description = "Get the responses to a request"
  lazy val method = HttpGetMethod
}

object AddRequest extends Endpoint {
  lazy val slug = "addRequest"
  lazy val path = "/request"
  lazy val stringFormat = "/request"
  lazy val description = "Add a new request"
  lazy override val requiredParams = List(RequestTitleParam, RequestDescriptionParam, RequestContributorParam)
  lazy override val optionalParams = List(RequestImageUriParam, RequestEndDateParam)
  lazy val method = HttpPostMethod
}

object Respond extends Endpoint {
  lazy val slug = "respond"
  lazy val path = "/request/:requestId/respond"
  lazy val stringFormat = "/request/%s/respond"
  lazy val description = "Respond to a request"
  lazy override val requiredParams = List(ResponseTextParam)
  lazy override val optionalParams = List(ResponseImageUriParam)
  lazy val method = HttpPostMethod
}

object GetResponse extends Endpoint {
  lazy val slug = "getResponse"
  lazy val path = "/response/:responseId"
  lazy val stringFormat = "/response/%s"
  lazy val description = "Get a response"
  lazy val method = HttpGetMethod
}