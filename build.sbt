name := """zaggle"""
organization := "kr.pe.lawrence"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala, SwaggerPlugin) //enable plugin

scalaVersion := "2.12.3"

libraryDependencies += guice

libraryDependencies ++= Seq(
  "io.swagger" %% "swagger-play2" % "1.6.1-SNAPSHOT",
  "org.webjars" % "swagger-ui" % "2.2.0"
)
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test

resolvers += Resolver.sonatypeRepo("snapshots")

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "kr.pe.lawrence.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "kr.pe.lawrence.binders._"

swaggerDomainNameSpaces := Seq("models")