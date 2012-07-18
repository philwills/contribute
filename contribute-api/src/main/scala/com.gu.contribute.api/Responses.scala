package com.gu.contribute.api

import model._

case class JournalistResponse(status: String, content: Journalist)

case class ContributorResponse(status: String, content: Contributor)

case class ContributorRequestResponse(status: String, content: List[Request])

case class RequestResponse(status: String, content: Request)

case class ResponseResponse(status: String, content: Response)