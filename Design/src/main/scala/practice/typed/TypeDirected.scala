package practice.typed

object TypeDirected extends App {

  println(implicitly[Ordering[Int]])

  implicit val n: Int = 42

  def f(implicit x: Int) = x

  println(f(0))

  implicit val world: String = "World"

  def greet(implicit name: String) = s"Hello, $name!"

  println(greet)

  def printValue[A:Show](a:A):Unit = {
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

