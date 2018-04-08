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
}
