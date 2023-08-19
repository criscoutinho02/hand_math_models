import org.apache.spark.sql.{DataFrame, SparkSession}

object Main {

  def readParquetFiles(folderPath: String, columnName: String): DataFrame = {
    val spark = SparkSession.builder()
      .appName("ParquetReader")
      .getOrCreate()

    val parquetFiles = spark.read.parquet(folderPath)

    val selectedColumns = Seq(columnName)
    val selectedDataFrame = parquetFiles.select(selectedColumns.head, selectedColumns.tail: _*)

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
    resultDataFrame.show()
  }


}