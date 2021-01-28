package practice.typed

import scala.annotation.tailrec

object TypeDirected extends App {

  println(implicitly[Ordering[Int]])

  implicit val n: Int = 42

  def f(implicit x: Int) = x

  println(f(0))

  implicit val world: String = "World"

  def greet(implicit name: String) = s"Hello, $name!"

  println(greet)

  def printValue[A: Show](a: A): Unit = {
    println(implicitly[Show[A]].apply(a))
  }

  printValue(42)
  println(implicitly[Show[Int]])
  println(implicitly[Show[Int]](Show.showInt))
}

trait LowPriorityImplicits {
  implicit val intOrdering: Ordering[Int] = Ordering.Int
}

object main extends LowPriorityImplicits {
  implicit val intReverseOrdering: Ordering[Int] = Ordering.Int.reverse

  def main(args: Array[String]): Unit = {
    println(List(1, 2, 3).min)
  }
}

trait Show[A] {
  def apply(a: A): String
}

object Show {
  implicit val showInt: Show[Int] = new Show[Int] {
    override def apply(a: Int): String = s"Int($a)"
  }
}

object Multiple extends App {

  case class Movie(title: String, rating: Int, duration: Int)

  val movies = Seq(
    Movie("Interstellar", 9, 169),
    Movie("Inglourious Basterds", 8, 140),
    Movie("Fight Club", 9, 139),
    Movie("Zodiac", 8, 157)
  )

  implicit def orderingList[A](implicit ord: Ordering[A]): Ordering[List[A]] =
    new Ordering[List[A]] {
      @tailrec
      def compare(xs: List[A], ys: List[A]): Int =
        (xs, ys) match {
          case (x :: xsTail, y :: ysTail) =>
            val c = ord.compare(x, y)
            if (c != 0) c else compare(xsTail, ysTail)
          case (Nil, Nil) => 0
          case (_, Nil) => 1
          case (Nil, _) => -1
        }
    }

  implicit def orderingPair[A, B](implicit orderingA: Ordering[A], orderingB: Ordering[B]): Ordering[(A, B)] =
    new Ordering[(A, B)] {
      def compare(pair1: (A, B), pair2: (A, B)): Int = {
        val firstCriteria = orderingA.compare(pair1._1, pair2._1)
        if (firstCriteria != 0) firstCriteria
        else orderingB.compare(pair1._2, pair2._2)
      }
    }

  println(List.unapplySeq(List(1, 2, 3)))
//  sort(movies)(movie => (movie.rating, movie.duration))
}
