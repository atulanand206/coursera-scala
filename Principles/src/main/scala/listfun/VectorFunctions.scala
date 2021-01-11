package listfun

object VectorFunctions extends App {

  val xs = Array(1, 2, 3, 44)
  println((xs map (x => x * 2)).mkString("Array(", ", ", ")"))

  val s = "He's A NightMare"
  println(s filter (c => c.isUpper))
  println(s takeRight (6))

  for (i <- 1 until 40 by 4) {
    println(i)
  }

  val it = List(1, 2, 3)
  val nt = List('a', 'b', 'c', 'f')
  println(it zip nt)
  println(it exists (x => x > 1))
  println(it forall (x => x > 1))
  println(nt.toString())
  println(it flatMap (x => List(x * 2 + 10, x)))
  println((s flatMap (x => List(x, '.'))).mkString(""))
  println("dramebaazA".min)
  println("dramebaaz".min)
  println(s.max)

  val value = (1 to 5) flatMap (x => (1 to 8) map (y => (x, y)))
  println(value)

  def scalarProduct(xs: Vector[Double], ys: Vector[Double]): Double =
    (xs zip ys).map(xy => xy._1 * xy._2).sum

  println(scalarProduct(Vector(10.0, 20.0, 30.0), Vector(5.3, 6.4, 6.5)))

  val numbers = Vector(1, 2, 3, -88)
  val people = Vector("Bob", "James", "Peter")

  println(numbers)
  println(people)
  println(people map (x => x + x))

  def isPrime(n: Int): Boolean = (2 until n) forall (i => n % i != 0)

  println(isPrime(5))
  println(isPrime(8))
}
