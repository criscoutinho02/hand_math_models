import Keys.*

lazy val commonSettings = Seq(
  organization := "com.coutinho",
  scalaVersion := "2.12.12",
  exportJars := true,
)

lazy val testSetting = Seq(
  Test / parallelExecution := false
)

lazy val writer = (project in file("law-newcomb-belford"))
  .settings(name := "law-newcomb-belford")
  .settings(commonSettings: _*)
  .settings(testSetting: _*)
  .settings(
    libraryDependencies ++= Dependencies.dependencies
  )

