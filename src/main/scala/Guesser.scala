class Guesser {

  val activePixels = Map(
    1 -> '_',
    3 -> '|',
    4 -> '_',
    5 -> '|',
    6 -> '|',
    7 -> '_',
    8 -> '|'
  )

  def flipPixels(cell: String): Set[String] =
    activePixels.map {
      case (index, activeChar) =>
        val inverse = cell(index) match {
          case ' ' => activeChar
          case _   => ' '
        }
        cell.updated(index, inverse).toString
    }.toSet

  def guesses(entry: Entry): List[Entry] = {
    val cells = entry.asCells

    (0 to 8)
      .flatMap{
        index =>
          flipPixels(cells(index)).map(
            flipped =>
              Entry(cells.updated(index, flipped))
          )}.toList
  }
}
