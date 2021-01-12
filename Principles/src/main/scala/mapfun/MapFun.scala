package mapfun

object MapFun extends App {
  val romanNumerals = Map("I" -> 1, "V" -> 5, "X" -> 10)
  println(romanNumerals)

  val capitalOfCountry = Map("US" -> "Washington", "Switzerland" -> "Bern")
  println(capitalOfCountry)

  def showCapital(country: String): String = capitalOfCountry.get(country) match {
    case Some(capital) => capital
    case None => "missing data"
  }

  println(showCapital("US"))
  println(showCapital("Algeria"))

  //  x^3 - 2x + 5
  Map(0 -> 5, 1 -> -2, 3 -> 1)

  class Polynomial(terms0: Map[Int, Double]) {
    def this(bindings: (Int, Double)*) = this(bindings.toMap)

    val terms = terms0 withDefaultValue 0.0

    def plus(other: Polynomial) = new Polynomial((other.terms foldLeft terms) (addTerm))

    def addTerm(terms: Map[Int, Double], term: (Int, Double)): Map[Int, Double] = {
      val (exp, coeff) = term
      terms + (exp -> (coeff + terms(exp)))
    }

    def +(other: Polynomial) = new Polynomial(terms ++ (other.terms map adjust))

    def adjust(term: (Int, Double)): (Int, Double) = {
      val (exp, coeff) = term
      exp -> (coeff + terms(exp))
    }

    override def toString: String =
      (for ((exp, coeff) <- terms.toList.sorted.reverse) yield coeff + "x^" + exp) mkString " + "
  }

  val p1 = new Polynomial(1 -> 2.0, 3 -> 4.0, 5 -> 6.2)
  val p2 = new Polynomial(Map(0 -> 3.0, 3 -> 7.0))
  val p3 = p1 + p2
  println(p1)
  println(p2)
  println(p3)
  val p4 = p2 plus p3
  println(p4)
}
