package practice

trait Expr {
  def isNumber: Boolean = false

  def isSum: Boolean = false

  def isProd: Boolean = false

  def isDivide: Boolean = false

  def isVar: Boolean = false

  def nameValue: String = throw new Error("Expr.name")

  def numValue: Int = throw new Error("Expr.numValue")

  def leftOp: Expr = throw new Error("Expr.leftOp")

  def rightOp: Expr = throw new Error("Expr.rightOp")

  def eval: Int = this match {
    case Number(n) => n
    case Var(name) => name.length
    case Sum(left, right) => left.eval + right.eval
    case Prod(left, right) => left.eval * right.eval
    case Divide(left, right) => if (right.eval != 0) left.eval / right.eval else throw new Error("Divide by zero")
  }

  def show: String = this match {
    case Number(n) => n.toString
    case Var(name) => name
    case Sum(left, right) => "(" + left.show + " + " + right.show + ") "
    case Prod(left, right) => "(" + left.show + " * " + right.show + ") "
    case Divide(left, right) => "(" + left.show + " / " + right.show + ") "
  }
}

case class Number(n: Int) extends Expr {
  override def isNumber: Boolean = true

  override def numValue: Int = n
}

case class Sum(left: Expr, right: Expr) extends Expr {
  override def isSum: Boolean = true

  override def leftOp: Expr = left

  override def rightOp: Expr = right
}

case class Prod(left: Expr, right: Expr) extends Expr {
  override def isProd: Boolean = true

  override def leftOp: Expr = left

  override def rightOp: Expr = right
}

case class Var(name: String) extends Expr {
  override def isVar: Boolean = true

  override def nameValue: String = name
}

case class Divide(left: Expr, right: Expr) extends Expr {
  override def isDivide: Boolean = true

  override def leftOp: Expr = left

  override def rightOp: Expr = right
}

object Expressions extends App {
  val expr: Expr = Sum(Sum(Number(5), Number(4)), Number(3))
  println(expr.eval)
  val expr2: Expr = Prod(expr, Var("abc de"))
  println(expr2.eval)
  println(expr2.show)
  val expr3: Expr = Divide(expr2, Number(2))
  println(expr3.eval)
  println(expr3.show)
  val expr4: Expr = Sum(Prod(Number(2), Var("x")), Var("y"))
  println(expr4.show)
  val expr5: Expr = Prod(Sum(Number(2), Var("x")), Var("y"))
  println(expr5.show)
}