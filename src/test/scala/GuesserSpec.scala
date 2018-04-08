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
}
