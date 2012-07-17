package com.gu.contribute.api.dispatcher

import com.gu.contribute.api._
import com.gu.contribute.api.model._
import org.scalatra.{SinatraRouteMatcher, RouteMatcher}
import com.gu.management.Loggable

class ApiDispatcher extends JsonDispatcher with Loggable {

  val ok = "OK"
  lazy val missingId = "An ID was not provided"
  lazy val userNotFound = "User was not found"
  lazy val requestNotFound = "Request was not found"

  implicit def endpoint2RouteMatcher(endpoint: Endpoint): RouteMatcher = new SinatraRouteMatcher(endpoint.path, requestPath)

  get(GetContributor) {
    val userId = multiParams("splat").headOption getOrElse halt(status = 400, reason = missingId)
    val user = Contributor(userId) getOrElse halt(status = 404, reason = userNotFound)
    ContributorResponse(ok, user)
  }

  get(GetContributorRequests) {
    val userId = multiParams("splat").headOption getOrElse halt(status = 400, reason = missingId)
    val requests = Request.getForContributor(userId)
    ContributorRequestResponse(ok, requests)
  }

  get(GetRequest) {
    val requestId = multiParams("splat").headOption getOrElse halt(status = 400, reason = missingId)
    val request = Request(requestId) getOrElse halt(status = 404, reason = requestNotFound)
    RequestResponse(ok, request)
  }

}