name := """zaggle"""
organization := "kr.pe.lawrence"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala, SwaggerPlugin) //enable plugin

scalaVersion := "2.12.3"

libraryDependencies += guice

libraryDependencies ++= Seq(
  "io.swagger" %% "swagger-play2" % "1.6.1-SNAPSHOT",
  "org.webjars" % "swagger-ui" % "2.2.0",

  "com.typesafe.play" %% "play-slick" % "3.0.3",
  "com.typesafe.play" %% "play-slick-evolutions" % "3.0.3",
  "com.h2database" % "h2" % "1.4.196",

  "com.pauldijou" %% "jwt-play-json" % "0.14.1"
)

libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test

resolvers += Resolver.sonatypeRepo("snapshots")

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "kr.pe.lawrence.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "kr.pe.lawrence.binders._"

swaggerDomainNameSpaces := Seq("models")