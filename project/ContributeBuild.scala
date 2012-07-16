import sbt._
import Keys._
import com.github.siasia._

object ContributeBuild extends Build {

  lazy val container = Container("container")

  lazy val rootSettings = Seq(
    libraryDependencies += "org.eclipse.jetty" % "jetty-webapp" % "7.4.5.v20110725" % "container",
    crossScalaVersions := Seq("2.9.1"),
    scalaVersion <<= (crossScalaVersions) { versions => versions.head },
    scalacOptions ++= Seq("-unchecked", "-deprecation"),
    organization := "com.gu"
  )

  lazy val contributeApi = Project("api", file("contribute-api"))

  lazy val web = Project("web", file(".")).settings(rootSettings: _*)

  lazy val root = Project("root", file(".")).aggregate(contributeApi)

}
