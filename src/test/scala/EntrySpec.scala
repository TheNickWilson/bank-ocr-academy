import Cells._
import org.scalatest.{MustMatchers, WordSpec}

class EntrySpec extends WordSpec with MustMatchers {

  ".asCells" must {
    "convert an entry of all zeroes" in {
      val allZeroes = Entry(
        " _  _  _  _  _  _  _  _  _ ",
        "| || || || || || || || || |",
        "|_||_||_||_||_||_||_||_||_|"
      )

      allZeroes.asCells mustEqual List(Zero, Zero, Zero, Zero, Zero, Zero, Zero, Zero, Zero)
    }

    "convert an entry of 123456789" in {
      val entry = Entry(
        "    _  _     _  _  _  _  _ ",
        "  | _| _||_||_ |_   ||_||_|",
        "  ||_  _|  | _||_|  ||_| _|"
      )

      entry.asCells mustEqual List(One, Two, Three, Four, Five, Six, Seven, Eight, Nine)
    }
  }
}
