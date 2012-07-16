import com.gu._

resolvers ++= Seq(
  "Local github" at "file://" + Path.userHome + "/guardian.github.com/maven/repo-snapshots",
  "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases/",
  "Typesafe Snapshots" at "http://repo.typesafe.com/typesafe/snapshots/",
  "Guardian GitHub Release" at "http://guardian.github.com/maven/repo-releases",
  "Guardian GitHub Snapshot" at "http://guardian.github.com/maven/repo-snapshots"
)

libraryDependencies ++= Seq(
  "javax.servlet" % "servlet-api" % "2.5" % "provided",
  "org.eclipse.jetty" % "jetty-webapp" % "7.4.5.v20110725" % "container",
  "org.scalatra" %% "scalatra" % "2.0.2",
  "org.fusesource.scalate" % "scalate-core" % "1.5.3",
  "org.scalatra" %% "scalatra-scalate" % "2.0.2",
  "org.mongodb" % "mongo-java-driver" % "2.7.3",
  "com.novus" %% "salat-core" % "0.0.8-SNAPSHOT",
  "net.liftweb" %% "lift-json" % "2.4-M4",
  "com.gu" %% "management-mongo" % "5.9",
  "com.gu" %% "management-logback" % "5.9",
  "com.gu" %% "management-servlet-api" % "5.9",
  "com.gu" % "configuration" % "2.9",
  "org.scalatest" %% "scalatest" % "1.6.1" % "test"
)
