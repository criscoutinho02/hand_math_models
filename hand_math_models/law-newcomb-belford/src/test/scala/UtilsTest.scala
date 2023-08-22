import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class UtilsTest extends AnyFlatSpec with SparkSpec {

  "convertCSVtoParquet" should "save parquet in destination" in {
    val fileCSV = "law-newcomb-belford/src/test/resources/youtube_statistics.csv"
    val destinationPath = "law-newcomb-belford/src/test/resources/youtube_statistics.parquet"
    Utils.convertCSVtoParquet(fileCSV, destinationPath)
    val df = spark.read.parquet(destinationPath)
    df.show()
    df.count() shouldEqual 995
  }

}
