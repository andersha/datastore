name := "realty-datastore"

version := "2.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, SbtWeb, BuildInfoPlugin)

scalaVersion := "2.12.4"

libraryDependencies ++= Seq(
  javaCore,
  ehcache,
  javaWs,
  filters,
  guice,
  "com.moonlit.logfaces" % "lfsappenders" % "4.3.3",
  "commons-io" % "commons-io" % "2.5",
  "org.webjars" %% "webjars-play" % "2.6.1",
  "org.webjars" % "jquery" % "2.1.4",
  "org.webjars" % "bootstrap" % "3.3.4",
  "org.easytesting" % "fest-assert" % "1.4" % "test"
)

credentials += Credentials("Sonatype Nexus Repository Manager", "nexus.eiendomsinfo.no", "build", "buildr0nne")

resolvers += "iBiblio" at "https://nexus.eiendomsinfo.no/content/groups/ne/"

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

startYear := Some(2014)

description := "Realty - Data Store"

organizationName := "Ambita AS"
