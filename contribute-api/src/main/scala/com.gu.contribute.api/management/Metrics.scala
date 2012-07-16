package com.gu.contribute.api.management

import com.gu.management._
import com.gu.management.mongodb._

object Metrics {
  lazy val all = HttpRequestsTimingMetric ::
    MongoRequests ::
    ClientErrorCounter ::
    ServerErrorCounter ::
    ExceptionCountMetric ::
    Nil ++ JvmMetrics.all
}