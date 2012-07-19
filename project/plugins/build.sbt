resolvers ++= Seq(
  "Web plugin repo" at "http://siasia.github.com/maven2",
  "Guardian GitHub Release" at "http://guardian.github.com/maven/repo-releases",
  Resolver.url("Typesafe repository", new java.net.URL("http://typesafe.artifactoryonline.com/typesafe/ivy-releases/"))(Resolver.defaultIvyPatterns)
)

libraryDependencies <+= sbtVersion(v => "com.github.siasia" %% "xsbt-web-plugin" % (v+"-0.2.7"))

scalaVersion := "2.9.1"