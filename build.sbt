name := "roqua-hl7-gateway"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "ca.uhn.hapi" % "hapi" % "2.2",
  "ca.uhn.hapi" % "hapi-base" % "2.2",
  "ca.uhn.hapi" % "hapi-structures-v24" % "2.2"
)

play.Project.playJavaSettings
