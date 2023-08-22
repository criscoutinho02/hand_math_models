import org.apache.spark.sql.SparkSession

trait SparkSpec {
  implicit val spark = SparkSession.builder()
    .master("local")
    .appName("local_spark")
    .getOrCreate();

}
