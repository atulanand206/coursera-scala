package mapfun

import scala.io.Source

object Mnemonics extends App {

  val in = Source.fromURL("https://raw.githubusercontent.com/dwyl/english-words/master/words_alpha.txt")
  val words = in.getLines.toList filter (_.length > 1)
  println(words.length)
  val mnem = Map('2' -> "ABC", '3' -> "DEF",
    '4' -> "GHI", '5' -> "JKL", '6' -> "MNO",
    '7' -> "PQRS", '8' -> "TUV", '9' -> "WXYZ")

  val charCode: Map[Char, Char] =
    for ((digit, str) <- mnem; ltr <- str) yield ltr -> digit

  println(charCode)

  def wordCode(word: String): String =
    word.toUpperCase map charCode

  println(wordCode("Java"))

  val wordsForNum: Map[String, Seq[String]] =
    words groupBy wordCode withDefaultValue Seq()

  //  println(wordsForNum)

  def encode(number: String): Set[List[String]] =
    if (number.isEmpty) Set(List())
    else {
      for {
        split <- 1 to number.length
        word <- wordsForNum(number take split)
        rest <- encode(number drop split)
      } yield word :: rest
    }.toSet

  def translate(number: String): Set[String] =
    encode(number) map (_ mkString " ")

  println(translate("7225247386"))
}
