package practice.generator

import java.util.Random

trait Generator[+T] {
  self =>
  this

  def generate: T

  def map[S](f: T => S): Generator[S] = new Generator[S] {
    override def generate: S = f(self.generate)
  }

  def flatMap[S](f: T => Generator[S]): Generator[S] = new Generator[S] {
    override def generate: S = f(self.generate).generate
  }
}

object IntegerGenerator extends Generator[Int] {
  val rand = new Random

  override def generate: Int = rand.nextInt
}

object BooleanGenerator extends Generator[Boolean] {
  override def generate: Boolean = IntegerGenerator.generate > 0
}

object PairGenerator extends Generator[(Int, Int)] {
  override def generate: (Int, Int) = (IntegerGenerator.generate, IntegerGenerator.generate)
}

object RandomGenerator extends App {

  println(IntegerGenerator.generate)
  println(IntegerGenerator.generate)
  println(IntegerGenerator.generate)
  println(IntegerGenerator.generate)
  println(BooleanGenerator.generate)
  println(BooleanGenerator.generate)
  println(BooleanGenerator.generate)
  println(BooleanGenerator.generate)
  println(BooleanGenerator.generate)
  println(PairGenerator.generate)
  println(PairGenerator.generate)
  println(PairGenerator.generate)

  def single[T](x: T): Generator[T] = new Generator[T] {
    override def generate: T = x
  }

  println(single(5).generate)

  def choose(lo: Int, hi: Int): Generator[Int] =
    for (x <- IntegerGenerator) yield lo + x % (hi - lo)

  println(choose(5, 8).generate)

  def oneOf[T](xs: T*): Generator[T] =
    for (idx <- choose(0, xs.length - 1)) yield xs(idx)

  println(oneOf("blue", "green", "yellow").generate)

  def emptyLists = single(Nil)

  def nonEmptyLists = for {
    head <- IntegerGenerator
    tail <- lists
  } yield head :: tail

  def lists: Generator[List[Int]] = for {
    isEmpty <- BooleanGenerator
    list <- if (isEmpty) emptyLists else nonEmptyLists
  } yield list

}