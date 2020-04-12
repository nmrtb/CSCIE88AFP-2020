import sbt._

object Dependencies {
  lazy val scalaTest = Seq(
    "org.scalatest" %% "scalatest" % "3.0.8" % Test
  )

  val circeVersion = "0.11.0"
  val fs2Version = "2.2.1"
  
  lazy val core = Seq(
    // cats FP libary
    "org.typelevel" %% "cats-core" % "1.6.1",
    "org.typelevel" %% "mouse" % "0.16",

    // support for JSON formats
    "io.circe" %% "circe-core" % circeVersion,
    "io.circe" %% "circe-generic" % circeVersion,

    // fs2
    "co.fs2" %% "fs2-core" % fs2Version,
    "co.fs2" %% "fs2-io" % fs2Version,

    // akka streams and alpakka
    "com.typesafe.akka" %% "akka-stream" % "2.5.23",
    "com.lightbend.akka" %% "akka-stream-alpakka-file" % "1.0.2",
    "com.lightbend.akka" %% "akka-stream-alpakka-s3" % "1.0.2",

    // support for typesafe configuration
    "com.github.pureconfig" %% "pureconfig" % "0.10.1",

    // logging
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
    "ch.qos.logback" % "logback-classic" % "1.2.3"
  )
}
