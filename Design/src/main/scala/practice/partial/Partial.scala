package practice.partial

object Partial extends App {

  val f: PartialFunction[String, String] = {
    case "ping" => "pong"
  }

  println(f.isDefinedAt("ping"))
  println(f.isDefinedAt("pong"))

  val g: PartialFunction[List[Int], String] = {
    case Nil => "one"
    case x :: y :: rest => "two"
  }

  println(g.isDefinedAt(List(1, 2, 3)))

  val h: PartialFunction[List[Int], String] = {
    case Nil => "one"
    case x :: rest =>
      rest match {
        case Nil => "two"
      }
  }

  println(h.isDefinedAt(List(1, 2, 3)))
}
