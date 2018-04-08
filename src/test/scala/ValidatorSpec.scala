import org.scalatest.{MustMatchers, WordSpec}

class ValidatorSpec extends WordSpec with MustMatchers {

  val validator = new Validator()

  ".checksum" must {

    "return true for account number 457508000" in {
      validator.checksum("457508000") mustEqual true
    }

    "return false for account number 664371495" in {
      validator.checksum("664371495") mustEqual false
    }

    "return true for account number 664371485" in {
      validator.checksum("664371485") mustEqual true
    }
  }
}
