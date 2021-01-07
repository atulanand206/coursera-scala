package exercise.funsets

import org.junit._

/**
 * This class is a test suite for the methods in object FunSets.
 *
 * To run this test suite, start "sbt" then run the "test" command.
 */
class FunSetSuite {

  import FunSets._

  @Test def `contains is implemented`: Unit = {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   * val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
  }

  /**
   * This test is currently disabled (by using @Ignore) because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", remvoe the
   *
   * @Ignore annotation.
   */
  @Test def `singleton set one contains one`: Unit = {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  @Test def `union contains all elements of each set`: Unit = {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }


  @Test def `intersect is empty if no intersection between 2 sets`: Unit = {
    new TestSets {
      val s = intersect(s1, s2)
      assert(!contains(s, 1), "intersect does not contain 1")
      assert(!contains(s, 2), "intersect does not contain 2")
    }
  }

  @Test def `intersect contains elements in both sets`: Unit = {
    new TestSets {
      val s = intersect(union(s1, s2), union(s2, s3))
      assert(!contains(s, 1), "intersect does not contain 1")
      assert(!contains(s, 3), "intersect does not contain 3")
      assert(contains(s, 2), "intersect contain 2")
    }
  }

  @Test def `diff contains elements in 1st set but not in 2nd set`: Unit = {
    new TestSets {
      val s = diff(union(s1, s2), union(s2, s3))
      assert(contains(s, 1), "intersect contain 1")
      assert(!contains(s, 2), "intersect does not contain 2")
      assert(!contains(s, 3), "intersect does not contain 3")
    }
  }

  @Test def `filter odd elements from the set`: Unit = {
    new TestSets {
      val s = filter(union(union(s1, s2), s3), x => x % 2 == 1)
      assert(contains(s, 1), "intersect contain 1")
      assert(!contains(s, 2), "intersect does not contain 2")
      assert(contains(s, 3), "intersect contain 3")
    }razy twitter tests based on functional recursion.
  }

  @Test def `forall all elements meet condition`: Unit = {
    new TestSets {
      val s = union(union(s1, s2), s3)
      assert(!forall(s, x => x < 1), "numbers are not smaller than 1")
      assert(forall(s, x => x > 0), "numbers are greater than 0")
    }
  }

  @Test def `exists at least one element that meet condition`: Unit = {
    new TestSets {
      val s = union(union(s1, s2), s3)
      assert(exists(s, x => x < 2))
      assert(exists(s, x => x < 100))
    }
  }

  @Test def `exists no element that meet condition`: Unit = {
    new TestSets {
      val s = union(union(s1, s2), s3)
      assert(!exists(s, x => x < 1))
    }
  }

  @Test def `map elements to new values`: Unit = {
    new TestSets {
      val s = union(union(s1, s2), s3)
      val mapped = map(s, x => x * x)
      assert(contains(mapped, 1))
      assert(contains(mapped, 4))
      assert(contains(mapped, 9))
    }
  }

  @Rule def individualTestTimeout = new org.junit.rules.Timeout(10 * 1000)
}
