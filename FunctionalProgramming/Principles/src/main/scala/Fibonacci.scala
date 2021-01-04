package main.scala

class Fibo {

  def fibImproper(n: Int): Int = {
    var a = 0
    var b = 1
    var i = 0
    while (i < n) {
      val prev_a = a
      a = b
      b = prev_a + b
      i = i + 1
    }
    a
  }

  def fibProper(n: Int): Int = {
    def fibIter(i: Int, a: Int, b: Int): Int =
      if (i == n) a else fibIter(i + 1, b, a + b)

    fibIter(0, 0, 1)
  }
}

object Fibonacci extends App {
  private val fibo = new Fibo()
  println("Improper : " + fibo.fibImproper(1))
  println("Proper : " + fibo.fibProper(4))
}
