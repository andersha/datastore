import play.PlayImport._
import play._
import sbt.Keys._

name := """realty-datastore"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.4"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
  "com.moonlit.logfaces" % "lfsappenders" % "4.1.2",
  "commons-io" % "commons-io" % "2.4"
)
