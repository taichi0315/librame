import Dependencies._

ThisBuild / scalaVersion     := "2.13.4"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "librame"
ThisBuild / organizationName := "librame"

lazy val commonSettings = Seq(
  resolvers += "Atlassian Releases" at "https://maven.atlassian.com/public/",
  libraryDependencies ++= Seq(
    scalaTest % Test
  )
)

lazy val root = (project in file("."))
  .settings(commonSettings)
  .aggregate(domain, application, adapter)

lazy val domain = (project in file("domain"))
  .settings(
    commonSettings,
    name := "librame-domain",
    libraryDependencies ++= Seq(
      "com.mohiva" %% "play-silhouette-password-bcrypt" % "7.0.0",
    )
  )

lazy val application = (project in file("application"))
  .settings(
    commonSettings,
    name := "librame-application",
    libraryDependencies ++= Seq(
      "org.atnos" %% "eff" % "5.12.0",
    )
  )
  .dependsOn(domain)

lazy val adapter = (project in file("adapter"))
  .settings(
    commonSettings,
    name := "librame-adapter",
    libraryDependencies ++= Seq(
      "org.atnos"              %% "eff" % "5.12.0",
      "com.typesafe.slick"     %% "slick" % "3.3.3",
      "com.mohiva"             %% "play-silhouette-password-bcrypt" % "7.0.0",
      "com.github.karelcemus"  %% "play-redis" % "2.6.1",
    )
  )
  .dependsOn(domain, application)
