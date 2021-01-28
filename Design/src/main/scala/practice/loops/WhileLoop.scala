package practice.loops

import scala.annotation.tailrec

object WhileLoop {

  @tailrec
  def WHILE(condition: => Boolean)(command: => Unit): Unit = {
    if (condition) {
      command
      WHILE(condition)(command);
    } else ()
  }

  @tailrec
  def REPEAT(condition: => Boolean)(command: => Unit): Unit = {
    command
    if (condition) ()
    else REPEAT(condition)(command);
  }
}
