package exercise

object PatternMatching extends App {

  def matchString(s: String): String = s match {
    case x => println("t")
      (x + x)
    case _ => println("s")
      ("_")
  }

  def matchStr(s: String): Unit = {
    println(matchString(s).length)
  }

  matchStr("")
  matchStr("sa")
}
