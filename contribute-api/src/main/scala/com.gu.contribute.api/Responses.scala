package com.gu.contribute.api

import model._

case class JournalistResponse(status: String, content: Journalist)

case class ContributorsResponse(status: String, content: List[Contributor])

case class ContributorResponse(status: String, content: Contributor)

case class ContributorRequestsResponse(status: String, content: List[Request])

case class RequestsResponse(status: String, content: List[Request])

case class RequestResponse(status: String, content: Request)

case class RequestResponsesResponse(status: String, content: List[Response])

case class ResponseResponse(status: String, content: Response)