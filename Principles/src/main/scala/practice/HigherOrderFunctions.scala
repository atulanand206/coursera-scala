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

  def sumF(f: Int => Int)(a: Int, b: Int): Int =
    if (a > b) 0 else f(a) + sumF(f)(a + 1, b)

  def product(f: Int => Int)(a: Int, b: Int): Int =
    if (a > b) 1 else f(a) * product(f)(a + 1, b)

  def factProduct(x: Int): Int =
    product(x => x)(1, x)

  def mapReduce(f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b: Int): Int =
    if (a > b) zero else combine(f(a), mapReduce(f, combine, zero)(a + 1, b))

  def sumReduce(a: Int, b: Int): Int =
    mapReduce(x => x, (x, y) => x + y, 0)(a, b)

  def productReduce(a: Int, b: Int): Int =
    mapReduce(x => x, (x, y) => x * y, 1)(a, b)

  def main(args: Array[String]): Unit = {
    println(factProduct(8))
    println(productReduce(1, 8))
    println(product(x => x * x)(1, 3))
    println(sumInts(1, 10))
    println(sumReduce(1, 10))
    println(sums(x => x, 1, 10))
    println(sumF(x => x * x * x)(1, 3))
    println(sumCubes(1, 3))
    println(sumFacts(1, 3))
    println(sum((x: Int) => x * x * x, 1, 3))
  }
}
