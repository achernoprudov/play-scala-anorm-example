name := "scala-play-rest-app"

version := "0.0.2-SNAPSHOT"

scalaVersion := "2.11.11"

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies += jdbc
libraryDependencies += evolutions
libraryDependencies += "com.adrianhurt" %% "play-bootstrap" % "1.0-P25-B3"
libraryDependencies += "com.typesafe.play" %% "anorm" % "2.5.0"
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "2.0.0" % Test

libraryDependencies += "net.liftweb" % "lift-json_2.11" % "2.6-M4"


