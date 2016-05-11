//import sbtbuildinfo.Plugin._

name := """realty-datastore"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, SbtWeb)

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  javaCore,
  cache,
  javaWs,
  "com.moonlit.logfaces" % "lfsappenders" % "4.2.0",
  "commons-io" % "commons-io" % "2.4",
  "org.webjars" %% "webjars-play" % "2.4.0-2",
  "org.webjars" % "jquery" % "2.1.4",
  "org.webjars" % "bootstrap" % "3.3.4"
)

credentials += Credentials("Sonatype Nexus Repository Manager", "nexus.eiendomsinfo.no", "build", "buildr0nne")

resolvers += "iBiblio" at "https://nexus.eiendomsinfo.no/content/groups/ne/"

sources in doc in Compile := List()

startYear := Some(2014)

description := "Realty - Data Store"

organizationName := "Ambita AS"
