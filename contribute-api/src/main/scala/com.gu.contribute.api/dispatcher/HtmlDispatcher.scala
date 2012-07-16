package com.gu.contribute.api.dispatcher

import com.gu.contribute.api.ConfigurationManager._
import org.scalatra._
import org.scalatra.scalate._
import com.gu.management.Loggable

class HtmlDispatcher extends ScalatraServlet with ScalatraKernel with ScalateSupport with Loggable {

  def render(file: String, params: Map[String, Any] = Map(), contentTypeHeader: String = "text/html;charset=UTF-8", cacheMaxAge: Int = 0) = {
    response.setHeader("Cache-Control", "public, max-age=%d" format cacheMaxAge)
    contentType = contentTypeHeader
    val enhancedParams = params ++ Map("appContext" -> context)
    templateEngine.layout("/WEB-INF/scalate/%s.ssp" format file, enhancedParams)
  }

}