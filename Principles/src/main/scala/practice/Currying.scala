package practice

import scala.annotation.tailrec

object Currying extends App {

  val tolerance = 0.0001

  def isCloseEnough(x: Double, y: Double) =
    Math.abs((x - y) / x) / x < tolerance

  def fixedPoint(f: Double => Double)(firstGuess: Double) = {
    @tailrec
    def iterate(guess: Double): Double = {
      val next = f(guess)
      if (isCloseEnough(guess, next)) next
      else iterate(next)
    }

    iterate(firstGuess)
  }

  def averageDamp(f: Double => Double)(x: Double) = (x + f(x)) / 2

  def sqrt(x: Double) = fixedPoint(x => 1 + x / 2)(1)

  def sqrtDamp(x: Double) = fixedPoint(averageDamp(y => x / y))(1)

  println(sqrt(4))
  println(sqrtDamp(4))
}
