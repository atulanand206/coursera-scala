package main.scala.practice

object Conditionals {

  def and(x: Boolean, y: Boolean): Boolean = {
    if (x) y else false
  }

  def or(x: Boolean, y: Boolean): Boolean = {
    if (x) x else y
  }

  def printExpectation(expected: Boolean, actual: Boolean): Unit = {
    printf("Expected: %s, Got: %s\n", expected, actual)
  }

  def main(args: Array[String]): Unit = {
    printExpectation(true, and(true, true))
    printExpectation(false, and(true, false))
    printExpectation(false, and(false, true))
    printExpectation(false, and(false, false))
    printExpectation(true, or(true, true))
    printExpectation(true, or(true, false))
    printExpectation(true, or(false, true))
    printExpectation(false, or(false, false))
  }
}
