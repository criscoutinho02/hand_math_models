import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class UtilsTest extends AnyFlatSpec with SparkSpec {

  "convertCSVtoParquet" should "save parquet in destination" in {
    val fileCSV = "law-newcomb-belford/src/test/resources/Historico_Totalizacao_Presidente_BR_2T_2022.csv"
    val destinationPath = "law-newcomb-belford/src/test/resources/urnaspresidente"
    Utils.convertCSVtoParquet(fileCSV, destinationPath)
    val df = spark.read.parquet(destinationPath)
    df.show()
    df.count() shouldEqual 995
  }

}
