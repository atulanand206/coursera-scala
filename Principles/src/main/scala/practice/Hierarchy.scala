package practice

import java.util.NoSuchElementException

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

trait List[T] {
  def isEmpty: Boolean

  def head: T

  def tail: List[T]
}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  def isEmpty: Boolean = false
}

class Nil[T] extends List[T] {
  def isEmpty: Boolean = true

  def head: Nothing = throw new NoSuchElementException("Nil.head")

  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
}

object List {
  def apply[T](): List[T] = new Nil[T]

  def apply[T](x: T): List[T] = new Cons[T](x, apply())

  def apply[T](x1: T, x2: T): List[T] = new Cons[T](x1, apply(x2))

  def apply[T](x1: T, x2: T, x3: T): List[T] = new Cons[T](x1, apply(x2, x3))
}

class ListMethods[T] {

  def singleton(elem: T) = new Cons[T](elem, new Nil[T])

  def nth(n: Int, list: List[T]): T =
    if (list.isEmpty) throw new IndexOutOfBoundsException()
    else if (n == 0) list.head
    else nth(n - 1, list.tail)
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
  val cons: Cons[Int] = new Cons(1, new Cons(2, new Cons(4, new Nil)))
  println(new ListMethods().nth(2, cons))
  //  println(List().head)
  println(List(2).head)
  println(List(1, 2).head)
  println(List(1, 2, 3).head)
}
