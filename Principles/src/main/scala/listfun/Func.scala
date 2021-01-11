package listfun

object Func extends App {

  def scaleList(xs: List[Double], factor: Double): List[Double] = xs match {
    case Nil => xs
    case y :: ys => (y * factor) :: scaleList(ys, factor)
  }

  val toScale = List(10.0, 1, 4, 6, 7, 8)
  println(scaleList(toScale, 5))
  println(toScale takeWhile (x => x > 5))
  println(toScale dropWhile (x => x > 5))
  println(toScale span (x => x > 5))
  println(toScale filter (x => x % 2 == 0))
  println(toScale filterNot (x => x % 2 == 0))
  println(toScale partition (x => x % 2 == 0))

  def pack[T](xs: List[T]): List[List[T]] = xs match {
    case Nil => Nil
    case x :: xs1 =>
      val (first, last) = xs span (y => y == x)
      first :: pack(last)
  }

  val data = List("a", "a", "a", "b", "c", "c", "a")
  val result = List(List("a", "a", "a"), List("b"), List("c", "c"), List("a"))
  println(pack(data))

  def encode[T](xs: List[T]): List[(T, Int)] = xs match {
    case Nil => Nil
    case x :: xs1 =>
      val (first, last) = xs span (y => y == x)
      (x, first.length) :: encode(last)
  }

  def encode2[T](xs: List[T]): List[(Any, Int)] = pack(xs) map (x => (x.head, x.length))

  println(encode(data))
  println(encode2(data))

  println(toScale.sum)

  val numbers = List(4, 4, 2, 4, 5, 6, 6)

  def sum(xs: List[Int]): Int = (0 :: xs) reduceLeft ((x, y) => x + y)

  println(sum(numbers))

  def product(xs: List[Int]): Int = (1 :: xs) reduceLeft (_ * _)

  println(product(numbers))

  def sumFold(xs: List[Int]): Int = (xs foldLeft 0) (_ + _)

  println(sumFold(numbers))

  def concat[T](xs: List[T], ys: List[T]): List[T] = (xs foldRight ys) (_ :: _)

  val list1 = List(1, 2, 3, 4, 1)
  val list2 = List(1, 2, 3, 4, 1)
  println(concat(list1, list2))

  def mapFun[T, U](xs: List[T], f: T => U): List[U] =
    (xs foldRight List[U]()) (f(_) :: _)

  def lengthFun[T](xs: List[T]): Int =
    (xs foldRight 0) ((_, y) => 1 + y)

  println(mapFun[Int, Int](list1, x => x))
  println(mapFun[Int, Int](list1, x => x * x))
  println(lengthFun(list1))
}
