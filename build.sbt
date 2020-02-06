import com.typesafe.config.ConfigFactory
name := """freshresumes"""
organization := "com.mindmover"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.0"

libraryDependencies += guice
libraryDependencies += "com.ticketfly" %% "play-liquibase" % "2.2"
libraryDependencies ++= Seq("mysql" % "mysql-connector-java" % "8.0.14")
libraryDependencies += "com.typesafe.play" %% "play" % "2.8.0"
libraryDependencies += "com.typesafe.sbt" % "sbt-interface" % "0.13.15"
libraryDependencies += ws
libraryDependencies ++= Seq(
  javaJpa,
  "org.hibernate" % "hibernate-entitymanager" % "5.4.1.Final" // replace by your jpa implementation
)
libraryDependencies ++= Seq(evolutions, jdbc)