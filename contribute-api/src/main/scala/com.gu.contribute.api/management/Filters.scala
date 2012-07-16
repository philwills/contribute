package com.gu.contribute.api.management

import com.gu.management._
import com.gu.management.servlet.ManagementFilter
import com.gu.contribute.api.ConfigurationManager._
import logback.LogbackLevelPage

class ApiManagementFilter extends ManagementFilter {

  lazy val pages =
    new ManifestPage() ::
      new HealthcheckManagementPage() ::
      new Switchboard(Switches.all) ::
      new PropertiesPage(config.toString) ::
      new LogbackLevelPage() ::
      StatusPage("contribute-api", Metrics.all) ::
      Nil

}