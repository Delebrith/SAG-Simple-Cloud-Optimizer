name := "simple-cloud-optimizer"

lazy val commonSettings = Seq(
  organization := s"pl.rpw",
  scalaVersion := "2.12.9",
  scalacOptions := Seq(
    "-unchecked",
    "-deprecation",
    "-encoding",
    "utf8",
    "-Xcheckinit",
    "-Ywarn-dead-code",
    "-Xfuture",
    "-Ywarn-unused",
    "-feature",
    "-Xfatal-warnings",
    "-language:postfixOps",
    "-language:existentials",
    "-deprecation:false"
  ),
  sources in(Compile, doc) := Seq.empty,
  resolvers ++= Seq(
    Resolver.jcenterRepo,
  )
)

lazy val dependencies = Seq(
  libraryDependencies ++= {
    val akkaV                   = "2.6.4"
    val scalaTestV              = "3.0.6"
    val wiremockV               = "2.23.2"

    Seq(
      "com.typesafe.akka"              %% "akka-actor"                                 % akkaV,

      "org.scalatest"                  %% "scalatest"                                  % scalaTestV                % Test,
      "com.github.tomakehurst"          % "wiremock"                                   % wiremockV                 % Test
    )
  }
)

lazy val testSettings = Seq(
  parallelExecution in Test := false,
  testOptions in Test ++= Seq(
    Tests.Argument(TestFrameworks.ScalaTest, "-o"),
    Tests.Argument(TestFrameworks.ScalaTest, "-u", "target/test-reports")
  )
)

lazy val root = (project in file("."))
  .settings(commonSettings)
  .settings(testSettings)
  .settings(dependencies)