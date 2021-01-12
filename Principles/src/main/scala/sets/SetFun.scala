package sets

object SetFun extends App {

  val fruit = Set("apple", "banana", "pear")
  val s = (1 to 6).toSet

  println(fruit)
  println(s)

  println(s map (_ + 2))
  println(fruit filter (_.startsWith("app")))
  println(s.nonEmpty)

  def queens(n: Int): Set[List[Int]] = {
    def isSafe(col: Int, queens: List[Int]): Boolean = {
      val row = queens.length
      val queensWithRow = (row - 1 to 0 by -1) zip queens
      queensWithRow forall {
        case (r, c) => col != c && math.abs(col - c) != row - r
      }
    }

    def placeQueens(k: Int): Set[List[Int]] =
      if (k == 0) Set(List())
      else
        for {
          queens <- placeQueens(k - 1)
          col <- 0 until n
          if isSafe(col, queens)
        } yield col :: queens

    placeQueens(n);
  }

  def show(queens: List[Int]): String = {
    val lines = for (col <- queens.reverse)
      yield Vector.fill(queens.length)("- ").updated(col, "X ").mkString
    "\n" + (lines mkString "\n")
  }

  println((queens(8) take 4 map show) mkString("\n"))
}
