ThisBuild / scalaVersion     := "2.13.3"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "librame"
ThisBuild / organizationName := "librame"

lazy val commonSettings = Seq(
  resolvers += "Atlassian Releases" at "https://maven.atlassian.com/public/",
  libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "3.1.1"
  )
)

lazy val playSettings = Seq(
  libraryDependencies ++= Seq(
    "com.typesafe.play" %% "play" % "2.8.2",
    jdbc
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
      "org.atnos" %% "eff-cats-effect" % "5.12.0",
    )
  )
  .dependsOn(domain)

lazy val adapter = (project in file("adapter"))
  .settings(
    commonSettings,
    playSettings,
    name := "librame-adapter",
    libraryDependencies ++= Seq(
      "com.typesafe.slick"    %% "slick" % "3.3.3",
      "mysql"                  % "mysql-connector-java"  % "5.1.48",
      "com.github.karelcemus" %% "play-redis" % "2.6.1",
      "org.tpolecat"          %% "doobie-core" % "0.9.0",
      "org.typelevel"         %% "cats-effect" % "2.2.0",
    )
  )
  .dependsOn(domain, application)
