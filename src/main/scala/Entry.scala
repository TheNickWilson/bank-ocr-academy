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
