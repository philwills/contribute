package com.gu.contribute.api.dispatcher

import com.gu.contribute.api._
import com.gu.contribute.api.model._
import org.scalatra.{SinatraRouteMatcher, RouteMatcher}
import com.gu.management.Loggable
import org.joda.time.DateTime
import org.bson.types.ObjectId

class ApiDispatcher extends JsonDispatcher with Loggable {

  val ok = "OK"
  lazy val missingId = "An ID was not provided"
  lazy val missingTitle = "Title was not provided"
  lazy val missingDescription = "Description was not provided"
  lazy val missingText = "Text was not provided"
  lazy val missingContributors = "No contributors were provided"
  lazy val invalidId = "Invalid ID provided"
  lazy val userNotFound = "User was not found"
  lazy val requestNotFound = "Request was not found"
  lazy val responseNotFound = "Response was not found"

  implicit def endpoint2RouteMatcher(endpoint: Endpoint): RouteMatcher = new SinatraRouteMatcher(endpoint.path, requestPath)

  get(GetJournalist) {
    val userId = params.get("userId") getOrElse halt(status = 400, reason = missingId)
    if(!ObjectId.isValid(userId)) halt(status = 400, reason = invalidId)
    val user = Journalist(userId) getOrElse halt(status = 404, reason = userNotFound)
    JournalistResponse(ok, user)
  }

  get(GetContributor) {
    val userId = params.get("userId") getOrElse halt(status = 400, reason = missingId)
    if(!ObjectId.isValid(userId)) halt(status = 400, reason = invalidId)
    val user = Contributor(userId) getOrElse halt(status = 404, reason = userNotFound)
    ContributorResponse(ok, user)
  }

  get(GetContributorRequests) {
    val userId = params.get("userId") getOrElse halt(status = 400, reason = missingId)
    if(!ObjectId.isValid(userId)) halt(status = 400, reason = invalidId)
    val requests = Request.getForContributor(userId)
    ContributorRequestsResponse(ok, requests)
  }

  get(GetRequest) {
    val requestId = params.get("requestId") getOrElse halt(status = 400, reason = missingId)
    if(!ObjectId.isValid(requestId)) halt(status = 400, reason = invalidId)
    val request = Request(requestId) getOrElse halt(status = 404, reason = requestNotFound)
    RequestResponse(ok, request)
  }

  get(GetRequestResponses) {
    val requestId = params.get("requestId") getOrElse halt(status = 400, reason = missingId)
    if(!ObjectId.isValid(requestId)) halt(status = 400, reason = invalidId)
    val responses = Response.getForRequest(requestId)
    RequestResponsesResponse(ok, responses)
  }

  post(AddRequest) {
    val journalistId = "5005c600ad727225cb4f87c1" //todo... this will come from the logged in user
    val title = params.get("title") getOrElse halt(status = 400, reason = missingTitle)
    val description = params.get("description") getOrElse halt(status = 400, reason = missingDescription)
    val contributorIds = multiParams("contributor").toList
    if(contributorIds.isEmpty) halt(status = 400, reason = missingContributors)
    contributorIds.foreach(objectId => if(!ObjectId.isValid(objectId)) halt(status = 400, reason = invalidId))
    val imageUri = params.get("imageUri")
    val endDate = params.get("endDate") match {
      case Some(date) => Some(new DateTime(date))
      case _ => None
    }
    val request = Request(title, description, imageUri, endDate, journalistId, contributorIds)
    request.upsert match {
      case Some(r) => status(204)
      case None => halt(status = 500)
    }
  }

  post(Respond) {
    val contributorId = "5005c600ad727225cb4f87c1" //todo... this will come from the logged in user
    val requestId = params.get("requestId") getOrElse halt(status = 400, reason = missingId)
    if(!ObjectId.isValid(requestId)) halt(status = 400, reason = invalidId)
    val text = params.get("text") getOrElse halt(status = 400, reason = missingText)
    val imageUri = params.get("imageUri")
    val response = Response(contributorId, requestId, text, imageUri)
    response.upsert match {
      case Some(r) => status(204)
      case None => halt(status = 500)
    }
  }

  get(GetResponse) {
    val responseId = params.get("responseId") getOrElse halt(status = 400, reason = missingId)
    if(!ObjectId.isValid(responseId)) halt(status = 400, reason = invalidId)
    val response = Response(responseId) getOrElse halt(status = 404, reason = responseNotFound)
    ResponseResponse(ok, response)
  }

}