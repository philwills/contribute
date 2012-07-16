package com.gu.contribute.api

class HttpMethod

object HttpPostMethod extends HttpMethod { override def toString = "POST" }
object HttpGetMethod extends HttpMethod { override def toString = "GET" }
object HttpDeleteMethod extends HttpMethod { override def toString = "DELETE" }
object HttpPutMethod extends HttpMethod { override def toString = "PUT" }