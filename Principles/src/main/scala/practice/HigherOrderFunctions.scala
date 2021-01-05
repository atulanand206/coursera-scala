package main.scala.practice

object HigherOrderFunctions {

  def sum(f: Int => Int, a: Int, b: Int): Int =
    if (a > b) 0 else f(a) + sum(f, a + 1, b)

  def id(x: Int): Int = x

  def cube(x: Int): Int = x * x * x

  def fact(x: Int): Int = if (x == 0) 1 else x * fact(x - 1)

  def sumInts(a: Int, b: Int): Int = sum(id, a, b)

  def sumCubes(a: Int, b: Int): Int = sum(cube, a, b)

  def sumFacts(a: Int, b: Int): Int = sum(fact, a, b)

  def main(args: Array[String]): Unit = {
    println(sumFacts(10, 20))
  }
}
