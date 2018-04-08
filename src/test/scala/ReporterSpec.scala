import org.scalatest.{MustMatchers, WordSpec}

class ReporterSpec extends WordSpec with MustMatchers {

  val reporter = new Reporter(new Parser, new Validator, new Guesser)

  ".prepareEntry" must {

    "output the account number for a valid account" in {
      val entry = Entry(
        "    _  _  _  _  _  _  _  _ ",
        "|_||_   ||_ | ||_|| || || |",
        "  | _|  | _||_||_||_||_||_|"
      )

      reporter.prepareEntry(entry) mustEqual "457508000"
    }

    "return the account number and ILL for an illegible account" in {
      val entry = Entry(
        "    _  _  _  _  _  _  _  _ ",
        " _||_   |   | ||_|| || || |",
        "  | _|  | _||_||_||_||_||_|"
      )

      reporter.prepareEntry(entry) mustEqual "?57?08000 ILL"
    }

    "return the corrected account number for an illegible account number with one valid guess" in {
      val entry = new Entry(
        "    _  _  _  _  _  _  _  _ ",
        " _||_   ||_ | ||_|| || || |",
        "  | _|  | _||_||_||_||_||_|"
      )

      reporter.prepareEntry(entry) mustEqual "457508000"
    }

    "return 711111111 when given 111111111" in {
      val entry = new Entry(
        "                           ",
        "  |  |  |  |  |  |  |  |  |",
        "  |  |  |  |  |  |  |  |  |"
      )

      reporter.prepareEntry(entry) mustEqual "711111111"
    }

    "return 777777177 when given 777777777" in {
      val entry = new Entry(
        " _  _  _  _  _  _  _  _  _ ",
        "  |  |  |  |  |  |  |  |  |",
        "  |  |  |  |  |  |  |  |  |"
      )

      reporter.prepareEntry(entry) mustEqual "777777177"
    }

    "return 200800000 when given 2000000000" in {
      val entry = new Entry(
        " _  _  _  _  _  _  _  _  _ ",
        " _|| || || || || || || || |",
        "|_ |_||_||_||_||_||_||_||_|"
      )

      reporter.prepareEntry(entry) mustEqual "200800000"
    }

    "return 888888888 AMB ['888886888', '888888880', '888888988'] when given 888888888" in {
      val entry = new Entry(
        " _  _  _  _  _  _  _  _  _ ",
        "|_||_||_||_||_||_||_||_||_|",
        "|_||_||_||_||_||_||_||_||_|"
      )

      reporter.prepareEntry(entry) mustEqual "888888888 AMB ['888886888', '888888988', '888888880']"
    }

    "return 555555555 AMB ['559555555', '555655555'] when given 555555555" in {
      val entry = new Entry(
        " _  _  _  _  _  _  _  _  _ ",
        "|_ |_ |_ |_ |_ |_ |_ |_ |_ ",
        " _| _| _| _| _| _| _| _| _|"
      )

      reporter.prepareEntry(entry) mustEqual "555555555 AMB ['559555555', '555655555']"
    }

    "return 490867715 when given 49086771?" in {
      val entry = new Entry(
        line1 = "    _  _  _  _  _  _     _ ",
        line2 = "|_||_|| ||_||_   |  |  | _ ",
        line3 = "  | _||_||_||_|  |  |  | _|"
      )

      reporter.prepareEntry(entry) mustEqual "490867715"
    }
  }
}
