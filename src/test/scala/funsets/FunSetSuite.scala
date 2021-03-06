package funsets

import org.scalatest.FunSuite


import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

    import FunSets._

  test("contains is implemented") {
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
   *   val s1 = singletonSet(1)
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
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {

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

  test("union contains all elements of each set") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  
  test("intersect contains the elements which exist in both sets") {
    new TestSets {
      val s11 = intersect(s1, s1)
      assert(contains(s11, 1), "Intersection 1")
      assert(!contains(s11, 2), "Intersection 2")
      
      val s12 = intersect(s1, s2)
      assert(!contains(s12, 1), "Intersection 1")
      assert(!contains(s12, 2), "Intersection 2")
    }
  }
  
  test("diff contains the elements which exist in first set and not exist in second sets") {
    new TestSets {
      val s11 = diff(s1, s1)
      assert(!contains(s11, 1), "Diff 1")
      
      val s12 = diff(s1, s2)
      assert(contains(s12, 1), "Diff 1")
      assert(!contains(s12, 2), "Diff 2")
      
      val s21 = diff(s2, s1)
      assert(!contains(s21, 1), "Diff 1")
      assert(contains(s21, 2), "Diff 2")
    }
  }
  
  test("filter elements from set") {
    new TestSets {
      val s = filter(s1, x => x == 1)
      assert(contains(s, 1), "Filter 1")
      assert(!contains(s, 2), "Filter 1")
      
      val s12 = filter(union(s1, s2), x => x > 1)
      assert(!contains(s12, 1), "Filter 1")
      assert(contains(s12, 2), "Filter 2")
      
      val s23 = filter(union(s2, s3), x => x / 3 == 1)
      assert(!contains(s23, 1), "Filter 1")
      assert(contains(s23, 3), "Filter 3")
    }
  }
  
  test("forall to go through the set") {
    new TestSets {
      val su1 = union(union(s1, s2), s3)
      assert(forall(su1, x => x > 0), "Forall bigger than 0")
      
      val su2 = union(s2, s3)
      assert(forall(su2, x => x > 1), "Forall bigger than 1")
    }
  }
  
  test("exists element in set") {
    new TestSets {
      val si1 = intersect(s1, s2)
      assert(!exists(si1, x => x > 0), "Not exist to empty set")
      
      val su1 = union(s1, s2)
      assert(exists(su1, x => x > 1), "Exist to set with (1, 2)")
      
      assert(!exists(su1, x => x > 3), "Not exist to set with (1, 2)")
      
    }
  }
  
  test("map a new set passing a function") {
    new TestSets {
      assert(contains(map(s2, x => x * 2), 4), "Set with 2, and function x * 2 have to have 4")
      
      assert(contains(map(s1, x => x - 1), 0), "Set with 1, and function x - 2 have to have 0")
      
      val su1 = union(s1, s2)
      assert(contains(map(su1, x => x * x), 1), "Set with (1, 2), and function x * x have to have 1")
      assert(contains(map(su1, x => x * x), 1), "Set with (1, 2), and function x * x have to have 4")
    }
  }

}
