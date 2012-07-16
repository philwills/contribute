package com.gu.contribute.api.dispatcher

import com.gu.contribute.api._
import com.gu.contribute.api.model._
import org.scalatra.{SinatraRouteMatcher, RouteMatcher}
import com.gu.management.Loggable

class ApiDispatcher extends JsonDispatcher with Loggable {

  val ok = "OK"
  lazy val missingId = "An ID was not provided"

  implicit def endpoint2RouteMatcher(endpoint: Endpoint): RouteMatcher = new SinatraRouteMatcher(endpoint.path, requestPath)

  get(GetUser) {
    "BOOM"
  }

  post(SearchUsers) {
    //todo
  }

}