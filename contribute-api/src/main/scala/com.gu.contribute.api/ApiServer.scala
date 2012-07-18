package com.gu.contribute.api

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.webapp.WebAppContext

object ApiServer extends App {
  val server = new Server(8080)

  val context = new WebAppContext()
  context.setContextPath("/")
  context.setResourceBase("./contribute-api/src/main/webapp")
  context.setParentLoaderPriority(true)

  server.setHandler(context)

  server.start()
  server.join()
}
