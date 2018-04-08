import Cells._
import org.scalacheck.Gen
import org.scalatest.prop.PropertyChecks
import org.scalatest.{MustMatchers, WordSpec}

class ParserSpec extends WordSpec with MustMatchers with PropertyChecks {

  val parser = new Parser()

  ".readCell" must {
    "recognise Zero" in {
      parser.readCell(Zero) mustEqual '0'
    }

    "recognise One" in {
      parser.readCell(One) mustEqual '1'
    }

    "recognise Two" in {
      parser.readCell(Two) mustEqual '2'
    }

    "recognise Three" in {
      parser.readCell(Three) mustEqual '3'
    }

    "recognise Four" in {
      parser.readCell(Four) mustEqual '4'
    }

    "recognise Five" in {
      parser.readCell(Five) mustEqual '5'
    }

    "recognise Six" in {
      parser.readCell(Six) mustEqual '6'
    }

    "recognise Seven" in {
      parser.readCell(Seven) mustEqual '7'
    }

    "recognise Eight" in {
      parser.readCell(Eight) mustEqual '8'
    }

    "recognise Nine" in {
      parser.readCell(Nine) mustEqual '9'
    }

    "not recognise invalid entries" in {
      val generator =
        Gen
          .listOfN(9, Gen.oneOf(Seq(' ', '_', '|')))
          .map(_.mkString)
          .suchThat(f => !Cells.All.contains(f))

      forAll(generator -> "invalid") {
        invalidChar =>
          intercept[IllegalArgumentException] {
            parser.readCell(invalidChar)
          }
      }
    }
  }
}
