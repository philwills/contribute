package com.gu.contribute.api

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.webapp.WebAppContext
import org.eclipse.jetty.servlet.ServletContextHandler
import java.io.File

object ApiServer extends App {
  val server = new Server(8080)

  val context = new ServletContextHandler(ServletContextHandler.SESSIONS)
  server.setHandler(context)
  val web = new WebAppContext(rootPath, "/")
  server.setHandler(web)
  server.start()
  server.join()

  def rootPath = getClass.getClassLoader.getResource("webapp").toExternalForm
}
