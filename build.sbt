import com.typesafe.config.ConfigFactory
import sbt.Keys.libraryDependencies
name := """freshresumes"""
organization := "com.mindmover"

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"


scalaVersion := "2.12.10"

libraryDependencies += "com.ticketfly" %% "play-liquibase" % "2.2"
libraryDependencies ++= Seq("mysql" % "mysql-connector-java" % "8.0.14")
libraryDependencies += "com.typesafe.sbt" % "sbt-interface" % "0.13.15"
libraryDependencies += "com.typesafe.play" %% "play" % "2.8.1"
libraryDependencies += "com.typesafe.play" %% "play-java-jpa" % "2.8.1"
libraryDependencies += ws
libraryDependencies ++= Seq(
  guice,
  javaJdbc,
  javaJpa,
  "org.hibernate" % "hibernate-core" % "5.4.9.Final",
  "javax.persistence" % "javax.persistence-api" % "2.2",
)
libraryDependencies ++= Seq(evolutions, jdbc)
Test / testOptions += Tests.Argument(TestFrameworks.JUnit, "-a", "-v")
scalacOptions ++= List("-encoding", "utf8", "-deprecation", "-feature", "-unchecked")
javacOptions ++= List("-Xlint:unchecked", "-Xlint:deprecation", "-Werror")
PlayKeys.externalizeResourcesExcludes += baseDirectory.value / "conf" / "META-INF" / "persistence.xml"