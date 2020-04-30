resolvers ++= Seq(
  "Seasar Repository" at "https://maven.seasar.org/maven2/",
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots")
)

addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.3.1")
