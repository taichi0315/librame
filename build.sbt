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
  .aggregate(domain, application, adapter)

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
  .dependsOn(domain)

lazy val adapter = (project in file("adapter"))
  .settings(
    commonSettings,
    resolvers += "Atlassian Releases" at "https://maven.atlassian.com/public/",
    libraryDependencies ++= Seq(
      "org.atnos"              %% "eff" % "5.12.0",
      "com.typesafe.slick"     %% "slick" % "3.3.3",
      "com.mohiva"             %% "play-silhouette-password-bcrypt" % "7.0.0",
      "com.github.karelcemus"  %% "play-redis" % "2.6.1",
    )
  )
  .dependsOn(domain, application)
