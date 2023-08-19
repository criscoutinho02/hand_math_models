import sbt._
object Dependencies {
  val sparkVersion = "3.3.2"

  val providedDependencies = Seq(
    "org.apache.spark" %% "spark-core" % sparkVersion,
    "org.apache.spark" %% "spark-sql" % sparkVersion,
    "org.apache.spark" %% "spark-mllib" % sparkVersion
  ) map (_ % Provided)

  lazy val scalatestVersion: String = "3.2.15"

  lazy val testDependencies = Seq(
      "org.scalatest" %% "scalatest" % scalatestVersion,
      "org.mockito" %% "mockito-scala" % "1.17.12"
  ) map (_ % sbt.Test)


  lazy val dependencies = providedDependencies ++ testDependencies

}
