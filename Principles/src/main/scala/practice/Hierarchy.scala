package practice

abstract class IntSet {
  def incl(x: Int): IntSet

  def contains(x: Int): Boolean

  def union(other: IntSet): IntSet
}

object Empty extends IntSet {
  def incl(x: Int): IntSet = NonEmpty(x, Empty, Empty)

  def contains(x: Int): Boolean = false

  def union(other: IntSet): IntSet = other

  override def toString: String = "."
}

case class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {
  def incl(x: Int): IntSet = {
    if (x < elem) NonEmpty(elem, left incl x, right)
    else if (x > elem) NonEmpty(elem, left, right incl x)
    else this
  }

  def contains(x: Int): Boolean = {
    if (x < elem) left contains x
    else if (x > elem) right contains x
    else true
  }

  def union(other: IntSet): IntSet = left union right union other incl elem

  override def toString: String = "{" + left + " " + elem + " " + right + "}"
}

object Hierarchy extends App {
  val root = NonEmpty(3, Empty, Empty)
  val root2 = NonEmpty(6, Empty, Empty)
  var newRoot2 = root2 incl 7
  println(root)
  var newRoot = root incl 4
  println(newRoot)
  newRoot = newRoot incl 5
  println(newRoot)
  newRoot = newRoot incl 1
  println(newRoot)
  println(newRoot union newRoot2)
}
