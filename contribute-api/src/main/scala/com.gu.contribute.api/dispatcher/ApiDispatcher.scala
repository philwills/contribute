package com.gu.contribute.api.dispatcher

import com.gu.contribute.api._
import com.gu.contribute.api.model._
import org.scalatra.{SinatraRouteMatcher, RouteMatcher}
import com.gu.management.Loggable
import org.joda.time.DateTime

class ApiDispatcher extends JsonDispatcher with Loggable {

  val ok = "OK"
  lazy val missingId = "An ID was not provided"
  lazy val missingText = "Text was not provided"
  lazy val userNotFound = "User was not found"
  lazy val requestNotFound = "Request was not found"
  lazy val responseNotFound = "Response was not found"

  implicit def endpoint2RouteMatcher(endpoint: Endpoint): RouteMatcher = new SinatraRouteMatcher(endpoint.path, requestPath)

  get(GetJournalist) {
    val userId = multiParams("splat").headOption getOrElse halt(status = 400, reason = missingId)
    val user = Journalist(userId) getOrElse halt(status = 404, reason = userNotFound)
    JournalistResponse(ok, user)
  }

  get(GetContributor) {
    val userId = multiParams("splat").headOption getOrElse halt(status = 400, reason = missingId)
    val user = Contributor(userId) getOrElse halt(status = 404, reason = userNotFound)
    ContributorResponse(ok, user)
  }

  get(GetContributorRequests) {
    val userId = multiParams("splat").headOption getOrElse halt(status = 400, reason = missingId)
    val requests = Request.getForContributor(userId)
    ContributorRequestsResponse(ok, requests)
  }

  get(GetRequest) {
    val requestId = multiParams("splat").headOption getOrElse halt(status = 400, reason = missingId)
    val request = Request(requestId) getOrElse halt(status = 404, reason = requestNotFound)
    RequestResponse(ok, request)
  }

  get(GetRequestResponses) {
    val requestId = multiParams("splat").headOption getOrElse halt(status = 400, reason = missingId)
    val responses = Response.getForRequest(requestId)
    RequestResponsesResponse(ok, responses)
  }

  post(Respond) {
    val contributorId = "5005c600ad727225cb4f87c1" //todo... this will come from the logged in user
    val requestId = multiParams("splat").headOption getOrElse halt(status = 400, reason = missingId)
    val text = params.get("text") getOrElse halt(status = 400, reason = missingText)
    val imageUri = params.get("imageUri")
    val response = Response(contributorId, requestId, text, imageUri)
    response.upsert match {
      case Some(r) => status(204)
      case None => halt(status = 500)
    }
  }

  get(GetResponse) {
    val responseId = multiParams("splat").headOption getOrElse halt(status = 400, reason = missingId)
    val response = Response(responseId) getOrElse halt(status = 404, reason = responseNotFound)
    ResponseResponse(ok, response)
  }

}