import Dependencies._

ThisBuild / scalaVersion := "2.13.4"
ThisBuild / version := "2.0.0-SNAPSHOT"
ThisBuild / organization := "io.github.neppyaga"

ThisBuild / organizationName := "Kushiro Taichi"
ThisBuild / startYear := Some(2021)
ThisBuild / licenses += ("MIT", new URL(
  "https://raw.githubusercontent.com/neppyaga/librame/develop/LICENSE"
))

lazy val commonSettings = Seq(
  resolvers ++= Seq(
    "Atlassian Releases" at "https://maven.atlassian.com/public/"
  ),
  libraryDependencies ++= Seq(
    scalaTest % Test
  ),
  scalacOptions ++= Seq(
    "-Wunused:imports"
  )
)

lazy val root = (project in file("."))
  .settings(commonSettings)
  .aggregate(domain, usecase, secondaryAdapter, primaryAdapter)

lazy val domain = (project in file("modules/domain"))
  .settings(
    commonSettings,
    name := "librame-domain",
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-core"                       % "2.3.1",
      "com.mohiva"    %% "play-silhouette-password-bcrypt" % "7.0.0"
    )
  )

lazy val usecase = (project in file("modules/usecase"))
  .settings(
    commonSettings,
    name := "librame-usecase",
    libraryDependencies ++= Seq(
      "org.atnos" %% "eff"             % "5.14.0",
      "org.atnos" %% "eff-monix"       % "5.14.0",
      "org.atnos" %% "eff-cats-effect" % "5.14.0"
    )
  )
  .dependsOn(domain)

lazy val secondaryAdapter = (project in file("modules/secondary-adapter"))
  .settings(
    commonSettings,
    name := "librame-secondary-adapter",
    libraryDependencies ++= Seq(
      "mysql"                  % "mysql-connector-java" % "5.1.48",
      "com.typesafe.slick"    %% "slick"                % "3.3.3",
      "org.tpolecat"          %% "doobie-core"          % "0.9.0",
      "com.github.karelcemus" %% "play-redis"           % "2.6.1",
      "com.typesafe.play"     %% "play-jdbc"            % "2.8.2",
      "io.monix"              %% "monix"                % "3.3.0"
    )
  )
  .dependsOn(domain, usecase)

lazy val primaryAdapter = (project in file("modules/primary-adapter"))
  .settings(
    commonSettings,
    name := "librame-primary-adapter"
  )
  .dependsOn(domain, usecase, secondaryAdapter)
