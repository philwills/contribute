package com.gu.contribute.api.dispatcher

import com.gu.contribute.api.Endpoint

class DocumentationDispatcher extends HtmlDispatcher {

  get("/") {
    val endpoints = Endpoint.all.values.toList.sortBy[String](x => x.slug)
    render("docs/index", Map("endpoints" -> endpoints))
  }

  get("/:slug") {
    val endpoints = Endpoint.all.values.toList.sortBy[String](x => x.slug)
    val endpoint = Endpoint.get(params("slug")) getOrElse halt (status = 404)
    render("docs/endpoint", Map("endpoint" -> endpoint, "endpoints" -> endpoints))
  }

}