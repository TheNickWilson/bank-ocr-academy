import org.scalatest.{MustMatchers, WordSpec}

class ReporterSpec extends WordSpec with MustMatchers {

  val reporter = new Reporter(new Parser, new Validator)

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

    "return the account number and ERR for a legible account that fails the checksum" in {
      val entry = Entry(
        " _  _     _  _        _  _ ",
        "|_ |_ |_| _|  |  ||_||_||_ ",
        "|_||_|  | _|  |  |  | _| _|"
      )

      reporter.prepareEntry(entry) mustEqual "664371495 ERR"
    }
  }
}
