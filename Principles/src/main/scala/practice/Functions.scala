package practice

import scala.annotation.tailrec

class Rational(x: Int, y: Int) {
  require(y != 0, "Denominator must be nonzero.")

  @tailrec
  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

  private val g: Int = gcd(x, y)

  val numer: Int = x / g

  val denom: Int = y / g

  def +(that: Rational): Rational =
    new Rational(numer * that.denom + that.numer * denom, denom * that.denom)

  def -(that: Rational): Rational = this + -that

  def unary_- : Rational =
    new Rational(-numer, denom)

  def <(that: Rational): Boolean =
    numer * that.denom < that.numer * denom

  def max(that: Rational): Rational =
    if (this < that) that else this

  override def toString: String = numer + "/" + denom
}

object Functions extends App {

  val x = new Rational(1, 3)
  val y = new Rational(5, 7)
  val z = new Rational(3, 2)
  println(x - y - z)
  println(y + y)
  println(x)
  val rational3 = x + y
  println(rational3)
  val rational4 = rational3 - y
  println(rational4)
  val rational5 = -rational4
  println(rational5)
  println(rational5 < rational4)
  println(rational5 max rational4)
  println(-new Rational(2, -4))
  println(-new Rational(2, 8))
  println(-new Rational(-2, 16))
  println(-new Rational(-16, -8))
}
