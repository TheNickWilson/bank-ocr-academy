case class Entry (line1: String, line2: String, line3: String) {

  def asCells(): List[String] = {

    val line1Chunks = line1.grouped(3).toList
    val line2Chunks = line2.grouped(3).toList
    val line3Chunks = line3.grouped(3).toList

    line1Chunks
      .zip(line2Chunks)
      .zip(line3Chunks)
      .map {
        case ((top, middle), bottom) => top + middle + bottom
      }
  }
}

object Entry {

  def apply(cells: List[String]): Entry = {

    val top = cells.flatMap(cell => cell.take(3)).mkString
    val middle = cells.flatMap(cell => cell.slice(3, 6)).mkString
    val bottom = cells.flatMap(cell => cell.slice(6, 9)).mkString

    new Entry(top, middle, bottom)
  }
}
