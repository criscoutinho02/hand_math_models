import org.apache.spark.sql.{DataFrame, SparkSession}

object Main {

  implicit val sparkSession = SparkSession.builder()
    .appName("ParquetReader")
    .getOrCreate()

  def readParquetFiles(folderPath: String, columnName: String)(implicit spark: SparkSession): DataFrame = {

    val parquetFiles = spark.read.parquet(folderPath)
    val selectedDataFrame = parquetFiles.select(columnName)

    selectedDataFrame
  }

  def main(args: Array[String]): Unit = {
    if (args.length < 2) {
      println("Usage: ParquetReader <folderPath> <columnName>")
      System.exit(1)
    }

    val folderPath = args(0)
    val columnName = args(1)

    val resultDataFrame = readParquetFiles(folderPath, columnName)
    val nbl = new NewcombBelfordLaw()
    nbl.calculateNewCombBelfordLaw(resultDataFrame, columnName)
  }


}