import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.funsuite.AnyFunSuiteLike
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class NewcombBelfordLawTest extends AnyFlatSpec with SparkSpec {

  val df = Main.readParquetFiles("law-newcomb-belford/src/test/resources/urnaspresidente", "subscribers")
  val nbl = new NewcombBelfordLaw()

  "extractFirstDigit" should "extract a df with first digit of colum" in {
    val nbl = new NewcombBelfordLaw()
    val result = nbl.countByFirstDigit(df, "subscribers")
    result.schema.map(_.name).toList shouldEqual List("firstDigit", "count")
  }

  "calculateNewCombBelfordLaw" should "calculate % of first digit in dataset" in {
    val result = nbl.calculateNewCombBelfordLaw(df, "subscribers")
    result.show()
    result.schema.map(_.name).toList shouldEqual List("firstDigit", "count", "percentage")
  }

  "compareDataframeWithLaw" should "compare the law with the dataset" in {
    val result = nbl.calculateNewCombBelfordLaw(df, "subscribers")
    nbl.compareDataframeWithLaw(result)
  }
}
