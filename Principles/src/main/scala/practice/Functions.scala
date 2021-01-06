package practice

import scala.annotation.tailrec

class Rational(x: Int, y: Int) {
  require(y != 0, "Denominator must be nonzero.")

  @tailrec
  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

  private val g: Int = gcd(x, y)

  val numer: Int = x / g

  val denom: Int = y / g

  def add(that: Rational): Rational =
    new Rational(numer * that.denom + that.numer * denom, denom * that.denom)

  def subtract(that: Rational): Rational = add(that.neg())

  def neg(): Rational =
    new Rational(-numer, denom)

  def less(that: Rational): Boolean =
    numer * that.denom < that.numer * denom

  def max(that: Rational): Rational =
    if (this.less(that)) that else this

  override def toString: String = numer + "/" + denom
}

object Functions extends App {

  val x = new Rational(1, 3)
  val y = new Rational(5, 7)
  val z = new Rational(3, 2)
  println(x.subtract(y).subtract(z))
  println(y.add(y))
  println(x)
  val rational3 = x.add(y)
  println(rational3)
  val rational4 = rational3.subtract(y)
  println(rational4)
  val rational5 = rational4.neg()
  println(rational5)
  println(rational5.less(rational4))
  println(rational5.max(rational4))
  println(new Rational(2, -4).neg())
  println(new Rational(2, 8).neg())
  println(new Rational(-2, 16).neg())
  println(new Rational(-16, -8).neg())
}
