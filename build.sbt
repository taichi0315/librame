import Dependencies._

ThisBuild / scalaVersion     := "2.13.3"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "librame"
ThisBuild / organizationName := "librame"

lazy val commonSettings = Seq(
  name := "librame",
  libraryDependencies += scalaTest % Test
)

lazy val root = (project in file("."))
  .settings(commonSettings)
  .aggregate(domain, application)

lazy val domain = (project in file("domain"))
  .settings(
    commonSettings,
    resolvers += "Atlassian Releases" at "https://maven.atlassian.com/public/",
    libraryDependencies ++= Seq(
      "com.mohiva"             %% "play-silhouette-password-bcrypt" % "7.0.0",
    )
  )

lazy val application = (project in file("application"))
  .settings(
    commonSettings,
    libraryDependencies ++= Seq(
      "org.atnos"              %% "eff" % "5.12.0",
    )
  )

resolvers += "Atlassian Releases" at "https://maven.atlassian.com/public/"

libraryDependencies ++= Seq(
  "com.typesafe.play"      %% "play-slick" % "5.0.0",
  "mysql"                   % "mysql-connector-java"  % "5.1.48",
  "com.mohiva"             %% "play-silhouette-password-bcrypt" % "7.0.0",
  "org.typelevel"          %% "cats-core" % "2.0.0",
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,
  "com.github.karelcemus"  %% "play-redis" % "2.6.1",
  "org.atnos"              %% "eff" % "5.12.0",
)

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
