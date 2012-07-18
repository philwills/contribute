import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "sieve"
    val appVersion      = "1.0"

    val appDependencies = Seq(
      "net.liftweb" %% "lift-json" % "2.4",
      "net.liftweb" %% "lift-json-ext" % "2.4",
      "org.mongodb" %% "casbah" % "2.4.1"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
      // Add your own project settings here      
    )

}
