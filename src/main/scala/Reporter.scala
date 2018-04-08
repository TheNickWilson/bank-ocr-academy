class Reporter(parser: Parser, validator: Validator, guesser: Guesser) {

  def prepareEntry(entry: Entry): String =
    parser.readEntry(entry) match {

      case validEntry if validator.checksum(validEntry) =>
        validEntry

      case invalid =>
        val guesses =
          guesser
            .guesses(entry)
            .map(parser.readEntry)

        val feasibleGuesses =
          guesses
            .filterNot(_.contains('?'))
            .filter(validator.checksum)

        feasibleGuesses match {
          case List(singlePossibility) => singlePossibility
          case List()                  => invalid + " ILL"
          case _                       => invalid + " AMB [" + prepareAmbiguousList(feasibleGuesses) + "]"
        }
    }

  private def prepareAmbiguousList(guesses: List[String]): String =
    guesses.map(guess => s"'$guess'").mkString(", ")
}
