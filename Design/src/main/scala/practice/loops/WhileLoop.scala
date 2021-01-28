package practice.loops

import scala.annotation.tailrec

object WhileLoop extends App {

  @tailrec
  def WHILE(condition: => Boolean)(command: => Unit): Unit = {
    if (condition) {
      command
      WHILE(condition)(command);
    } else ()
  }

  def equalsToZero(): Boolean = 0 == 0
  def equalsToOne(): Boolean = 0 == 1
  def print() : Unit = println("A")
//  WHILE(equalsToZero())(print())

  @tailrec
  def REPEAT(condition: => Boolean)(command: => Unit): Unit = {
    command
    if (condition) ()
    else REPEAT(condition)(command);
  }

  REPEAT(equalsToZero())(print())
}
