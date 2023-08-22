import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers


class MainTest extends AnyFlatSpec with Matchers with SparkSpec {

  "Main" should "print the file name and column name" in {
    val file = "test.txt"
    val columnName = "Column1"
    val args = Array(file, columnName)

    val outputStream = new java.io.ByteArrayOutputStream()
    Console.withOut(outputStream) {
      Main.main(args)
    }
    val output = outputStream.toString.trim

    output shouldEqual s"($file,$columnName)"
  }

  it should "throw an ArrayIndexOutOfBoundsException if not enough arguments are provided" in {
    val args = Array.empty[String]

    an[ArrayIndexOutOfBoundsException] should be thrownBy {
      Main.main(args)
    }
  }

  "readParquetFile" should "return a dataset" in {
    val folderPath = "law-newcomb-belford/src/test/resources/youtube_statistics.parquet"
    val columnName = "subscribers"

    val resultDataFrame = Main.readParquetFiles(folderPath, columnName)
    resultDataFrame.count() shouldBe 995
  }
}