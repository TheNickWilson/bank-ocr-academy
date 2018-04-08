class Validator {

  def checksum(accountNumber: String): Boolean = {
    val multipliers = 9 to 1 by -1

    val checksum =
      accountNumber
        .map(_.asDigit)
        .zip(multipliers)
        .map(x => x._1 * x._2)
        .sum

    checksum % 11 == 0
  }
}
