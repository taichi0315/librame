import Dependencies._

ThisBuild / scalaVersion     := "2.13.4"
ThisBuild / version          := "0.5.0-SNAPSHOT"
ThisBuild / organization     := "io.github.neppyaga"

lazy val commonSettings = Seq(
  resolvers ++= Seq(
    "Atlassian Releases" at "https://maven.atlassian.com/public/",
  ),
  libraryDependencies ++= Seq(
    scalaTest % Test
  ),
  scalacOptions ++= Seq(
    "-Wunused:imports",
  ),
)

lazy val root = (project in file("."))
  .settings(commonSettings)
  .aggregate(domain, usecase, secondaryAdapter)

lazy val domain = (project in file("domain"))
  .settings(
    commonSettings,
    name := "librame-domain",
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-core" % "2.3.1",
      "com.mohiva" %% "play-silhouette-password-bcrypt" % "7.0.0"
    )
  )

lazy val usecase = (project in file("usecase"))
  .settings(
    commonSettings,
    name := "librame-usecase",
    libraryDependencies ++= Seq(
      "org.atnos" %% "eff" % "5.12.0",
      "org.atnos" %% "eff-cats-effect" % "5.12.0",
    )
  )
  .dependsOn(domain)

lazy val secondaryAdapter = (project in file("secondary-adapter"))
  .settings(
    commonSettings,
    name := "librame-secondary-adapter",
    libraryDependencies ++= Seq(
      "mysql"                 % "mysql-connector-java"  % "5.1.48",
      "com.typesafe.slick"    %% "slick" % "3.3.3",
      "org.tpolecat"          %% "doobie-core" % "0.9.0",
      "com.github.karelcemus" %% "play-redis" % "2.6.1",
      "com.typesafe.play"     %% "play-jdbc" % "2.8.2",
    )
  )
  .dependsOn(domain, usecase)

lazy val primaryAdapter = (project in file("primary-adapter"))
  .settings(
    commonSettings,
    name := "librame-primary-adapter"
  )
  .dependsOn(domain, usecase, secondaryAdapter)

// For Scalafix
ThisBuild / semanticdbEnabled := true
ThisBuild / semanticdbVersion := scalafixSemanticdb.revision
ThisBuild / scalafixDependencies += "com.nequissimus" %% "sort-imports" % "0.5.5"
