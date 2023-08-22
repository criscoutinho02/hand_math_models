import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions._

class NewcombBelfordLaw(implicit spark: SparkSession) {

  def countByFirstDigit(df: DataFrame, columName: String) ={
    df.withColumn("firstDigit",substring(col(columName),1,1))
      .groupBy("firstDigit")
      .count()
      .orderBy("firstDigit")
  }

  def calculateNewCombBelfordLaw(df: DataFrame, columName: String) ={
    val countByFirstDigit = this.countByFirstDigit(df, columName)
    val total = countByFirstDigit.agg(sum("count")).first().getLong(0)
    countByFirstDigit.withColumn("percentage", col("count") / total * 100)
  }

}
