import com.typesafe.sbt.web.SbtWeb
import play.PlayImport.PlayKeys._
import play.PlayImport._
import play._
import sbt.Keys._

name := """realty-datastore"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, SbtWeb)

scalaVersion := "2.11.4"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
  "com.moonlit.logfaces" % "lfsappenders" % "4.1.2",
  "commons-io" % "commons-io" % "2.4",
  "org.webjars" %% "webjars-play" % "2.3.0-2",
  "org.webjars" % "jquery" % "2.1.3",
  "org.webjars" % "bootstrap" % "3.3.1"
)

packagedArtifacts += ((artifact in playPackageAssets).value -> playPackageAssets.value)

credentials += Credentials("Sonatype Nexus Repository Manager", "nexus.eiendomsinfo.no", "build", "buildr0nne")

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

resolvers += "iBiblio" at "https://nexus.eiendomsinfo.no/content/groups/ne/"

publishTo <<= version { v: String =>
  val nexus = "https://nexus.eiendomsinfo.no/"
  if (v.trim.endsWith("SNAPSHOT")) Some("snapshots" at nexus + "content/repositories/ne-play-snapshots")
  else                             Some("releases" at nexus + "content/repositories/ne-play-releases")
}

sources in doc in Compile := List()

startYear := Some(2014)

description := "Realty - Data Store"

organizationName := "Ambita AS"