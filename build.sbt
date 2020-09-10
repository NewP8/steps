name := "steps"
version := "0.1"
scalaVersion := "2.13.3"
organization := "it.cgn"

val macwire = "com.softwaremill.macwire" %% "macros" % "2.3.3" % "provided"
val scalaTest = "org.scalatest" %% "scalatest" % "3.1.1" % Test

lagomCassandraEnabled in ThisBuild := false
lagomKafkaEnabled in ThisBuild := false

lazy val `steps` = (project in file("."))
  .aggregate(`steps-api`, `steps-impl`)

lazy val `steps-api` = (project in file("steps-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `steps-impl` = (project in file("steps-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      // lagomScaladslPersistenceCassandra,
      // lagomScaladslKafkaBroker,
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .settings(lagomForkedTestSettings)
  .dependsOn(`steps-api`)
