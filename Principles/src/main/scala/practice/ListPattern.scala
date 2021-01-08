package practice

object ListPattern extends App {

  import scala.collection.immutable.List

  def listMatch[T](l: List[T]) = l match {
    case x :: y :: z :: a :: zs => true
    case _ => false
  }

  def removeAt[T](xs: List[T], n: Int): List[T] = (xs take n) ::: (xs drop n + 1)

  var value: List[Int] = List(1, 2, 3)
  println(value.length)
  println(listMatch(value))
  value = List(1, 2, 3, 5)
  println(listMatch(value))
  println(removeAt(value, 2))
}
