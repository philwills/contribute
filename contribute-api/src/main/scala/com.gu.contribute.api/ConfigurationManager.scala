package com.gu.contribute.api

import com.gu.conf.ConfigurationFactory

object ConfigurationManager {

  def apply(propertyName: String) = config getStringProperty propertyName

  def get(propertyName: String) = {
    val result = config getStringProperty(propertyName, null)
    if (result != null) Some(result)
    else None
  }

  def getOrElse(propertyName: String, defaultValue: String) = config.getStringProperty(propertyName, defaultValue)

  val config = new ConfigurationFactory getConfiguration("contribute-api", "conf/contribute-api")

  lazy val mongoDbName = ConfigurationManager("mongo.database.name")
  lazy val mongoDbUsername = ConfigurationManager("mongo.database.username")
  lazy val mongoDbPassword = ConfigurationManager("mongo.database.password")
  lazy val context = ConfigurationManager.getOrElse("context", "")
  lazy val mongoDbHost = ConfigurationManager.get("mongo.database.host")
  lazy val mongoDbUriOption = ConfigurationManager.get("mongo.database.uri")
  lazy val mongoInClusterMode = ! ConfigurationManager.get("mongo.standalone").map(_.toBoolean).getOrElse(false)

  lazy val configString = config.toString

  def toSet(propertyName: String) = ((config getStringProperty propertyName) split ",").toSet

}