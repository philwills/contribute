package com.gu.contribute.api.model

case class ContributorResponse(status: String, content: Contributor)

case class ContributorRequestResponse(status: String, content: List[Request])

case class RequestResponse(status: String, content: Request)