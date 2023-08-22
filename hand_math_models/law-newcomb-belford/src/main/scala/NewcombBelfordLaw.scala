import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions._

class NewcombBelfordLaw(implicit spark: SparkSession) {

  import spark.implicits._

  def countByFirstDigit(df: DataFrame, columName: String) ={
    df.withColumn("firstDigit",substring(col(columName),1,1))
      .groupBy("firstDigit")
      .count()
      .orderBy("firstDigit")
  }

  def calculateNewCombBelfordLaw(df: DataFrame, columName: String) ={
    val countByFirstDigit = this.countByFirstDigit(df, columName)
    val total = countByFirstDigit.agg(sum("count")).first().getLong(0)
    val result = countByFirstDigit.withColumn("percentage", col("count") / total * 100)
    compareDataframeWithLaw(result)
  }

  def compareDataframeWithLaw(df: DataFrame) : Unit = {
    val law = getLaw()
    df.map(row => {
      val firstDigit = row.getAs("firstDigit").toString.toInt
      val percentage = row.getAs("percentage").toString.toDouble
      val lawPercentage = law(firstDigit)
      val diff = percentage - lawPercentage
      (firstDigit, percentage, lawPercentage, diff)
    }).show(truncate = false)

  }

  def getLaw(): Map[Int, Double] = {
    Map(
      1 -> 30.1,
      2 -> 17.6,
      3 -> 12.5,
      4 -> 9.7,
      5 -> 7.9,
      6 -> 6.7,
      7 -> 5.8,
      8 -> 5.1,
      9 -> 4.6,
    )
  }

}
