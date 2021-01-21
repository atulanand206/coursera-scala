package practice.stream

object StreamFun extends App {

  val xs = LazyList.cons(1, LazyList.cons(2, LazyList.empty))

  def lazyListRange(lo: Int, hi: Int): LazyList[Int] =
    if (lo >= hi) LazyList.empty
    else LazyList.cons(lo, lazyListRange(lo + 1, hi))

  println(lazyListRange(1, 10).take(4).toList)

  def expr = {
    val x = {
      print("x");
      1
    }
    lazy val y = {
      print("y");
      2
    }

    def z = {
      print("z");
      3
    }

    z + y + x + z + y + x
  }

  expr
  println()

  def from(n: Int): LazyList[Int] = n #:: from(n + 1)

  //  println(from(1000).length)

  val nats = from(0)
  val m4s = nats.map(_ * 4)
  println((m4s take 1000).toList(10))

  //  println(nats(5))

  def sieve(s: LazyList[Int]): LazyList[Int] =
    s.head #:: sieve(s.tail filter (_ % s.head != 0))

  val primes = sieve(from(2))
  println(primes.take(10).toList)

}
