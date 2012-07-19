import sbt._
import Keys._
//import com.github.siasia._
//import PluginKeys._
//import WebPlugin._
//import com.gu.SbtDistPlugin._
import sbtassembly.Plugin._
import AssemblyKeys._

object ContributeBuild extends Build {

//  lazy val container = Container("container")

  lazy val contributeApi = Project("api", file("."), settings = Project.defaultSettings ++ Seq(
    libraryDependencies += "org.eclipse.jetty" % "jetty-webapp" % "7.4.5.v20110725",
    crossScalaVersions := Seq("2.9.1"),
    scalaVersion <<= (crossScalaVersions) { versions => versions.head },
    scalacOptions ++= Seq("-unchecked", "-deprecation"),
    organization := "com.gu",
    resourceGenerators in Compile <+= (resourceManaged, baseDirectory) map { (managedBase, base) =>
      val webappBase = base / "src" / "main" / "webapp"
      for {
        (from, to) <- webappBase ** "*" x rebase(webappBase, managedBase / "main" / "webapp")
      } yield {
        Sync.copy(from, to)
        to
      }
    }
  ) ++ assemblySettings)
}
