resolvers ++= Seq(
  "Web plugin repo" at "http://siasia.github.com/maven2",
  "Guardian GitHub Release" at "http://guardian.github.com/maven/repo-releases",
  Resolver.url("Typesafe repository", new java.net.URL("http://typesafe.artifactoryonline.com/typesafe/ivy-releases/"))(Resolver.defaultIvyPatterns),
  "zentrope" at "http://zentrope.com/maven",
  "sbt-idea-repo" at "http://mpeltonen.github.com/maven/",
Resolver.url("artifactory", url("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases"))(Resolver.ivyStylePatterns)
)

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "0.11.0")

addSbtPlugin("com.zentrope" %% "xsbt-scalate-precompile-plugin" % "1.7")

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.8.3")