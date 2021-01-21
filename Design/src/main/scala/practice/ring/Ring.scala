package practice.ring

trait Ring[A] {
  def plus(x: A, y: A): A

  def mult(x: A, y: A): A

  def inverse(x: A): A

  def zero: A

  def one: A

  def plusAssociativity[A](x: A, y: A, z: A)(implicit ring: Ring[A]): Boolean =
    ring.plus(ring.plus(x, y), z) == ring.plus(x, ring.plus(y, z))
}

object Ring {
  implicit val ringInt: Ring[Int] = new Ring[Int] {
    override def plus(x: Int, y: Int): Int = x + y

    override def mult(x: Int, y: Int): Int = x * y

    override def inverse(x: Int): Int = -x

    override def zero: Int = 0

    override def one: Int = 1
  }

  def main(args: Array[String]): Unit = {
    println(Ring.ringInt.plusAssociativity(5, 7, 9))

    // Numeric trait has implementation of the various properties to conform to a ring algebraic structure.
  }
}
