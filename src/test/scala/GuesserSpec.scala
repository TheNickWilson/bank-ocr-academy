import org.scalatest.{MustMatchers, WordSpec}

class GuesserSpec extends WordSpec with MustMatchers {

  val guesser = new Guesser

  ".flipPixels" must {

    "turn on each active pixel individually when they're blank" in {
      val input = "         "
      guesser.flipPixels(input) mustEqual Set(
        " _       ",
        "   |     ",
        "    _    ",
        "     |   ",
        "      |  ",
        "       _ ",
        "        |"
      )
    }

    "turn off each active pixel individually when they're active" in {
      val input = " _ |_||_|"
      guesser.flipPixels(input) mustEqual Set(
        "   |_||_|",
        " _  _||_|",
        " _ | ||_|",
        " _ |_ |_|",
        " _ |_| _|",
        " _ |_|| |",
        " _ |_||_ "
      )
    }
  }

  ".guesses" must {

    "flip the pixels of each character in an entry" in {
      val entry = new Entry(
        " _  _  _  _  _  _  _  _  _ ",
        "| || || || || || || || || |",
        "|_||_||_||_||_||_||_||_||_|"
      )

      val guesses = guesser.guesses(entry)
      guesses.size mustEqual 7 * 9
      guesses.distinct mustEqual guesses
    }
  }
}
