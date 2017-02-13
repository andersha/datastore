import de.johoop.jacoco4sbt.JacocoPlugin._
import de.johoop.jacoco4sbt._

name := """realty-datastore"""

version := "1.1-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, SbtWeb, BuildInfoPlugin)

scalaVersion := "2.11.8"

jacoco.settings

jacoco.reportFormats in jacoco.Config := Seq(
  XMLReport(encoding = "utf-8"),
  ScalaHTMLReport(withBranchCoverage = true))

libraryDependencies ++= Seq(
  javaCore,
  cache,
  javaWs,
  "com.moonlit.logfaces" % "lfsappenders" % "4.2.0",
  "commons-io" % "commons-io" % "2.4",
  "org.webjars" %% "webjars-play" % "2.5.0-4",
  "org.webjars" % "jquery" % "2.1.4",
  "org.webjars" % "bootstrap" % "3.3.4",
  "org.easytesting" % "fest-assert" % "1.4" % "test"
)

credentials += Credentials("Sonatype Nexus Repository Manager", "nexus.eiendomsinfo.no", "build", "buildr0nne")

resolvers += "iBiblio" at "https://nexus.eiendomsinfo.no/content/groups/ne/"

parallelExecution in jacoco.Config := false

buildInfoKeys ++= Seq[BuildInfoKey](
  "builtAt" -> {
    val dtf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    dtf.setTimeZone(java.util.TimeZone.getTimeZone("UTC"))
    dtf.format(new java.util.Date())
  },
  "builtAtMillis" -> {
    System.currentTimeMillis()
  }
)

buildInfoKeys ++= Seq[BuildInfoKey](
  BuildInfoKey.map(name) {
    case (k, v) => "project" + k.capitalize -> v.capitalize
  }
)

buildInfoPackage := "buildinfo"

sources in doc in Compile := List()

startYear := Some(2014)

description := "Realty - Data Store"

organizationName := "Ambita AS"
