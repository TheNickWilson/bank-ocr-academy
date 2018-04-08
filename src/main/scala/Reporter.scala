class Reporter(parser: Parser, validator: Validator) {

  def prepareEntry(entry: Entry): String =
    parser.readEntry(entry) match {

      case illegible  if illegible.contains('?') =>
        illegible + " ILL"

      case validEntry if validator.checksum(validEntry) =>
        validEntry

      case badChecksum =>
        badChecksum + " ERR"
    }
}
