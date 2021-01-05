package main.scala.practice

import scala.annotation.tailrec

object HigherOrderFunctions {

  def sum(f: Int => Int, a: Int, b: Int): Int =
    if (a > b) 0 else f(a) + sum(f, a + 1, b)

  def fact(x: Int): Int = if (x == 0) 1 else x * fact(x - 1)

  def sumInts(a: Int, b: Int): Int = sum(x => x, a, b)

  def sumCubes(a: Int, b: Int): Int = sum(x => x * x * x, a, b)

  def sumFacts(a: Int, b: Int): Int = sum(fact, a, b)

  def sums(f: Int => Int, a: Int, b: Int): Int = {
    @tailrec
    def loop(a: Int, acc: Int): Int = {
      if (a > b) acc
      else loop(a + 1, f(a) + acc)
    }

    loop(a, 0)
  }

  def main(args: Array[String]): Unit = {
    println(sumInts(1, 3))
    println(sums(x => x, 1, 3))
    println(sumFacts(1, 3))
    println(sumCubes(1, 3))
    println(sum((x: Int) => x * x * x, 1, 3))
  }
}
