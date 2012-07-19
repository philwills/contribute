resolvers ++= Seq (
  "zentrope" at "http://zentrope.com/maven",
  "sbt-idea-repo" at "http://mpeltonen.github.com/maven/"
)

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "0.11.0")

addSbtPlugin("com.zentrope" %% "xsbt-scalate-precompile-plugin" % "1.6")
