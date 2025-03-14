import scala.collection.Seq

homepage in ThisBuild := Some(url("https://github.com/slamdata/quasar-destination-azure"))

scmInfo in ThisBuild := Some(ScmInfo(
  url("https://github.com/slamdata/quasar-destination-azure"),
  "scm:git@github.com:slamdata/quasar-destination-azure.git"))

// Include to also publish a project's tests
lazy val publishTestsSettings = Seq(
  Test / packageBin / publishArtifact := true)

lazy val QuasarVersion = IO.read(file("./quasar-version")).trim
val ArgonautVersion = "6.2.3"
val AsyncBlobstoreVersion = "0.1.5"
val Fs2Version = "1.0.5"

lazy val root = project
  .in(file("."))
  .settings(noPublishSettings)
  .aggregate(core)
  .enablePlugins(AutomateHeaderPlugin)

lazy val core = project
  .in(file("core"))
  .settings(name := "quasar-destination-azure")
  .settings(
    performMavenCentralSync := false,
    publishAsOSSProject := false,
    quasarPluginName := "azure-dest",
    quasarPluginQuasarVersion := QuasarVersion,
    quasarPluginDestinationFqcn := Some("quasar.destination.s3.AzureDestinationModule$"),
    quasarPluginDependencies ++= Seq(
      "com.slamdata" %% "async-blobstore-core" % AsyncBlobstoreVersion,
      "io.argonaut" %% "argonaut" % ArgonautVersion,
      "co.fs2" %% "fs2-core" % Fs2Version))
  .enablePlugins(AutomateHeaderPlugin, QuasarPlugin)
