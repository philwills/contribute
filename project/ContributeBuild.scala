import sbt._
import Keys._
import com.github.siasia._
import PluginKeys._
import WebPlugin._
import com.gu.SbtDistPlugin._

object ContributeBuild extends Build {

  lazy val container = Container("container")

  lazy val contributeApi = Project("api", file("contribute-api")).settings(webSettings: _*)

  lazy val root = Project("root", file(".")).aggregate(contributeApi)

  lazy val web = Project("web", file(".")).settings(rootSettings: _*).settings(port in container.Configuration := 9081)

  lazy val rootSettings = Seq(
    libraryDependencies += "org.eclipse.jetty" % "jetty-webapp" % "7.4.5.v20110725" % "container",
    crossScalaVersions := Seq("2.9.1"),
    scalaVersion <<= (crossScalaVersions) { versions => versions.head },
    scalacOptions ++= Seq("-unchecked", "-deprecation"),
    organization := "com.gu"
  ) ++ container.deploy("/api" -> contributeApi) ++ dist ++ (port in container.Configuration := 9081)

  lazy val project = Project("project", file("project"))

  lazy val buildNumber = System.getProperty("build.number", "DEV")

  def webappProject(project: Project, outputPath: String) = {
    sbt.Keys.`package` in (project, Compile) map(_ -> outputPath)
  }

  def deployFiles = baseDirectory in project map { dir =>
    val deployRoot = dir / "deploy"
    (deployRoot ***) x rebase (deployRoot, "")
  }

  lazy val dist: Seq[Setting[_]] = distSettings ++ Seq(
    distFiles <+= webappProject(contributeApi, "packages/contribute-api/webapps/api.war"),
    distFiles <++= deployFiles
  )

}