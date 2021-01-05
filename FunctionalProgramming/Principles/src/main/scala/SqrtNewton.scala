package main.scala

import scala.annotation.tailrec

object SqrtNewton {

  def squareRoot(x: Double): Double = {
    def isGoodEnough(guess: Double, x: Double): Boolean = Math.abs(1 - (guess * guess) / x) < 0.000001

    def improve(guess: Double, x: Double): Double = (guess + (x / guess)) / 2

    @tailrec
    def estimateSquareRoot(guess: Double, x: Double): Double =
      if (isGoodEnough(guess, x)) guess
      else estimateSquareRoot(improve(guess, x), x)

    estimateSquareRoot(1, x)
  }

  def printSquareRoot(x: Double): Unit = printf("%.51f : %.50f\n", x, squareRoot(x))

  def main(args: Array[String]): Unit = {
    printSquareRoot(4)
    printSquareRoot(5)
    printSquareRoot(6)
    printSquareRoot(7)
    printSquareRoot(8)
    printSquareRoot(9)
    printSquareRoot(0.001)
    printSquareRoot(0.1e-20)
    printSquareRoot(1.0e20)
    printSquareRoot(0.1e-50)
    printSquareRoot(1.0e50)
  }
}
