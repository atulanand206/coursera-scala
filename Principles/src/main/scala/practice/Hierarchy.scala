package practice

abstract class IntSet {
  def incl(x: Int): IntSet

  def contains(x: Int): Boolean
}

object Empty extends IntSet {
  override def incl(x: Int): IntSet = NonEmpty(x, Empty, Empty)

  override def contains(x: Int): Boolean = false

  override def toString: String = "."
}

case class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {
  override def incl(x: Int): IntSet = {
    if (x < elem) NonEmpty(elem, left incl x, right)
    else if (x > elem) NonEmpty(elem, left, right incl x)
    else this
  }

  override def contains(x: Int): Boolean = {
    if (x < elem) left contains x
    else if (x > elem) right contains x
    else true
  }

  override def toString: String = "{"+ left + " " + elem + " "  + right +"}"
}

object Hierarchy extends App {
  val root = NonEmpty(3, Empty, Empty)
  println(root)
  var newRoot = root incl 4
  println(newRoot)
  newRoot = newRoot incl 5
  println(newRoot)
  newRoot = newRoot incl 1
  println(newRoot)
}
