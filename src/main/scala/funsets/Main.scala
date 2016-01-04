package funsets

object Main extends App {
  import FunSets._
  println(contains(singletonSet(1), 1))
  
  val s2 = singletonSet(2)
  val s3 = singletonSet(3)
  val s4 = singletonSet(4)
  val s5 = singletonSet(5)
  val sUnion = union(union(s2,s3), s4)
  println(contains(sUnion, 1))
  println(contains(sUnion, 2))
  println(contains(sUnion, 3))
  println(contains(sUnion, 4))
  println(contains(sUnion, 5))
}
