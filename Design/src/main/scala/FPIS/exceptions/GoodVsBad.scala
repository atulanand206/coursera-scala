package FPIS.exceptions

object GoodVsBad extends App {

  def failingFn(i: Int): Int = {
//        val y: Int = throw new Exception("fail!")
    try {
      val x = 42 + 5
      x + ({
        throw new Exception("fail!")
      }: Int)
    } catch {
      case e: Exception => 43
    }
  }

  println(failingFn(12))
}
