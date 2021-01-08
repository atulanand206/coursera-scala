package practice

abstract class Nat {
  def isZero: Boolean

  def predecessor: Nat

  def successor: Nat = new Succ(this)

  def +(that: Nat): Nat

  def -(that: Nat): Nat
}

object Zero extends Nat {
  override def isZero: Boolean = true

  override def predecessor: Nothing = throw new ArithmeticException("0.predecessor")

  override def +(that: Nat): Nat = that

  override def -(that: Nat): Nat = if (that.isZero) this else throw new ArithmeticException("negative number")

  override def toString: String = "0"
}

class Succ(n: Nat) extends Nat {
  override def isZero: Boolean = false

  override def predecessor: Nat = n

  override def +(that: Nat): Nat = new Succ(n + that)

  override def -(that: Nat): Nat = if (that.isZero) this else n - that.predecessor

  override def toString: String = ???
}

object Naturals extends App {

  val zero = Zero
  val one = new Succ(zero)
  val two = one + one
  val three = two + one

  // these checks yield false
  assert((two - zero) == two)
  assert(three.predecessor == two)

  // if I define the numbers from two onward differently, that creates another issues
  val two2 = new Succ(one)
  val three2 = new Succ(two)

  // these checks yield false
  assert(one.successor == two2)
  assert((one + one) == two2)
  assert(one.successor == two2)
  assert(three2.predecessor == two2)
}
