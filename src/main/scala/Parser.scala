import Cells._

class Parser {

  def readCell(input: String): Char = input match {
    case Zero  => '0'
    case One   => '1'
    case Two   => '2'
    case Three => '3'
    case Four  => '4'
    case Five  => '5'
    case Six   => '6'
    case Seven => '7'
    case Eight => '8'
    case Nine  => '9'
    case _     => throw new IllegalArgumentException
  }
}
