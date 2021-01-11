package practice

class HigherOrderListFunctions {

  import scala.collection.immutable.List

  //  def scaleList(xs: List[Double], factor: Double): List[Double] = xs match {
  //    case Nil => xs
  //    case y :: ys => (y * factor) :: scaleList(ys, factor)
  //  }



  def scaleList(xs: List[Double], factor: Double): List[Double] = xs map (x => x * factor)
}
