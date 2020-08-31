import sbt.Keys._
import play.sbt.PlaySettings

lazy val root = (project in file("."))
  .enablePlugins(PlayService, PlayLayoutPlugin, Common)
  .settings(
    name := "phonebook",
    scalaVersion := "2.13.2",
    libraryDependencies ++= Seq(
      guice,
      "io.lemonlabs" %% "scala-uri" % "1.5.1",
      "net.codingwell" %% "scala-guice" % "4.2.6",
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,
      "org.postgresql" % "postgresql" % "42.2.12",
      "com.typesafe.slick" %% "slick" % "3.3.2",
      "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0",
      "com.typesafe.play" %% "play-slick" % "5.0.0",
      "net.logstash.logback" % "logstash-logback-encoder" % "6.2",
      "org.joda" % "joda-convert" % "2.2.1"
    ),
    scalacOptions ++= Seq("-encoding", "utf8")
  )

  libraryDependencies += jdbc % Test

  libraryDependencies += "org.webjars" % "bootstrap" % "3.3.6"