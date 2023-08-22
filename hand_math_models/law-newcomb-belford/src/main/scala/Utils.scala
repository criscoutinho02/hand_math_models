import org.apache.spark.sql.SparkSession

object Utils {

  def convertCSVtoParquet(fileCSV: String, destinationPath: String)(implicit spark: SparkSession) = {
    val df = spark.read.options(Map("inferSchema" -> "true", "delimiter" -> ",", "header" -> "true"))
      .csv(fileCSV)
    df.write.parquet(destinationPath)
  }

}
