name := """realty-datastore"""

version := "1.1-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, SbtWeb, BuildInfoPlugin)

enablePlugins(sbtdocker.DockerPlugin)

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  javaCore,
  cache,
  javaWs,
  "com.moonlit.logfaces" % "lfsappenders" % "4.2.0",
  "commons-io" % "commons-io" % "2.4",
  "org.webjars" %% "webjars-play" % "2.4.0-2",
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

sources in doc in Compile := List()

startYear := Some(2014)

description := "Realty - Data Store"

organizationName := "Ambita AS"

dockerfile in docker := {
  val jarFile = sbt.Keys.`package`.in(Compile, packageBin).value
  val classpath = (managedClasspath in Compile).value
  val mainclass = mainClass.in(Compile, packageBin).value.getOrElse(sys.error("Expected exactly one main class"))
  val jarTarget = s"/app/${jarFile.getName}"
  // Make a colon separated classpath with the JAR file
  val classpathString = classpath.files.map("/app/" + _.getName).mkString(":") + ":" + jarTarget
  new Dockerfile {
    // Base image
    from("java")
    // Add all files on the classpath
    add(classpath.files, "/app/")
    // Add the JAR file
    add(jarFile, jarTarget)
    // On launch run Java with the classpath and the main class
    entryPoint("java", "-cp", classpathString, mainclass)
  }
}

// Set names for the image
imageNames in docker := Seq(
  ImageName(namespace = Some("ambita"),
    repository = name.value,
    tag = Some(version.value))
)
