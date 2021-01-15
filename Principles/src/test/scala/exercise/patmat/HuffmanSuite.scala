package patmat

import org.junit._
import org.junit.Assert.{assertEquals, assertFalse, assertTrue}

class HuffmanSuite {

  import Huffman._

  trait TestTrees {
    val t1 = Fork(Leaf('a', 2), Leaf('b', 3), List('a', 'b'), 5)
    val t2 = Fork(Fork(Leaf('a', 2), Leaf('b', 3), List('a', 'b'), 5), Leaf('d', 4), List('a', 'b', 'd'), 9)
  }


  @Test def `weight of a larger tree (10pts)`: Unit =
    new TestTrees {
      assertEquals(5, weight(t1))
    }


  @Test def `chars of a larger tree (10pts)`: Unit =
    new TestTrees {
      assertEquals(List('a', 'b', 'd'), chars(t2))
    }

  @Test def `string2chars hello world`: Unit =
    assertEquals(List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'), string2Chars("hello, world"))

  @Test def `times hello world`: Unit =
    assertEquals(List((' ', 1), ('d', 1), ('e', 1), ('h', 1), ('l', 3), ('o', 2), ('r', 1), ('w', 1)), times(string2Chars("hello world")))

  @Test def `make ordered leaf list for some frequency table (15pts)`: Unit =
    assertEquals(List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 3)), makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))))

  @Test def `single code tree`: Unit = {
    assertTrue(singleton(List(Leaf('e', 1))))
    assertFalse(singleton(List(Leaf('e', 1), Leaf('z', 1))))
  }

  @Test def `combine of some leaf list (15pts)`: Unit = {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    val leaflist2 = List(Leaf('e', 1), Leaf('t', 2))
    assertEquals(List(Fork(Leaf('e', 1), Leaf('t', 2), List('e', 't'), 3), Leaf('x', 4)), combine(leaflist))
    assertEquals(List(Fork(Leaf('e', 1), Leaf('t', 2), List('e', 't'), 3)), combine(leaflist2))
  }

  @Test def `create code tree`: Unit = {
    val forkABCDEFGH = Fork(Leaf('A',8),Fork(Fork(Fork(Leaf('G',1),Leaf('H',1),List('G', 'H'),2),Fork(Leaf('E',1),Leaf('F',1),List('E', 'F'),2),List('G', 'H', 'E', 'F'),4),Fork(Fork(Leaf('C',1),Leaf('D',1),List('C', 'D'),2),Leaf('B',3),List('C', 'D', 'B'),5),List('G', 'H', 'E', 'F', 'C', 'D', 'B'),9),List('A', 'G', 'H', 'E', 'F', 'C', 'D', 'B'),17)
    val listA8B3CDEFGH = List('A', 'A', 'B', 'H', 'B', 'A', 'A', 'G', 'B', 'F', 'E', 'A', 'C', 'D', 'A', 'A', 'A')
    assertEquals(forkABCDEFGH, createCodeTree(listA8B3CDEFGH))
  }

  @Test def `french code`: Unit = {
    println(frenchCode)
    assertTrue(true)
  }

  @Test def `decode and encode a very short text should be identity (10pts)`: Unit =
    new TestTrees {
      assertEquals("ab".toList, decode(t1, encode(t1)("ab".toList)))
    }


  @Rule def individualTestTimeout = new org.junit.rules.Timeout(10 * 1000)
}
