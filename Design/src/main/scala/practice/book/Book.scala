package practice.book

case class Book(title: String, authors: List[String])

object BookApp extends App {
  val books: Set[Book] = Set(
    Book(title = "Structure and Interpretation of Computer Programs",
      authors = List("Abelson, Harald", "Sussman, Gerald J.")),
    Book(title = "Introduction to Functional Programming",
      authors = List("Bird, Richard", "Wadler, Phil")),
    Book(title = "Effective Java",
      authors = List("Bloch, Joshua")),
    Book(title = "Effective Java 2",
      authors = List("Bloch, Joshua")),
    Book(title = "Java Puzzlers",
      authors = List("Bloch, Joshua", "Gafter, Neal"))
  )

  val query1 = for (b <- books; a <- b.authors if a startsWith "Bird,") yield b.title
  println(query1)

  val query1Higher1 = books.flatMap(b =>
    for (a <- b.authors if a startsWith "Bird,") yield b.title)
  println(query1Higher1)

  val query1Higher2 = books.flatMap(b =>
    for (a <- b.authors withFilter (x => x startsWith "Bird,")) yield b.title)
  println(query1Higher2)

  val query1Higher3 = books.flatMap(b =>
    (b.authors withFilter (x => x startsWith "Bird,")) map (_ => b.title))
  println(query1Higher3)

  val query2 = for {
    b <- books
    if b.title.indexOf("Program") >= 0
  } yield b.title

  println(query2)

  val query3 =
    for {
      b1 <- books
      b2 <- books
      if b1.title < b2.title
      a1 <- b1.authors
      a2 <- b2.authors
      if a1 == a2
    } yield a1

  println(query3)
}
