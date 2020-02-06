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


//lazy val root = (project in file("."))
//  .enablePlugins(PlayJava)
//  .settings(
//    name := """freshresumes""",
//    organization := "com.mindmover",
//    resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases",
//    version := "1.0-SNAPSHOT",
//    scalaVersion := "2.12.10",
//    libraryDependencies += "com.ticketfly" %% "play-liquibase" % "2.2",
//    libraryDependencies ++= Seq("mysql" % "mysql-connector-java" % "8.0.14"),
//    libraryDependencies ++= Seq(
//      ws,
//      jdbc,
//      evolutions,
//      guice,
//      javaJpa,
//      "org.hibernate" % "hibernate-core" % "5.4.9.Final",
//      javaWs % "test",
//      "org.awaitility" % "awaitility" % "4.0.1" % "test",
//      "org.assertj" % "assertj-core" % "3.14.0" % "test",
//      "org.mockito" % "mockito-core" % "3.1.0" % "test",
//    ),
//    Test / testOptions += Tests.Argument(TestFrameworks.JUnit, "-a", "-v"),
//    scalacOptions ++= List("-encoding", "utf8", "-deprecation", "-feature", "-unchecked"),
//    javacOptions ++= List("-Xlint:unchecked", "-Xlint:deprecation", "-Werror"),
//    PlayKeys.externalizeResourcesExcludes += baseDirectory.value / "conf" / "META-INF" / "persistence.xml"
//  )
