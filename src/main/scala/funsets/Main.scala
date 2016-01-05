package funsets

object Main extends App {
  import FunSets._
  println("Contains")
  println(contains(singletonSet(1), 1))
  
  val s2 = singletonSet(2)
  val s3 = singletonSet(3)
  val s4 = singletonSet(4)
  val s5 = singletonSet(5)
  val sUnion = union(union(s2,s3), s4)
  
  println("Union")
  println(contains(sUnion, 1))
  println(contains(sUnion, 2))
  println(contains(sUnion, 3))
  println(contains(sUnion, 4))
  println(contains(sUnion, 5))
  
  println("Intersection")
  println(contains(intersect(s2,s2), 2))
  println(contains(intersect(s2,s2), 3))
  
  println(contains(intersect(s2,s3), 2))
  println(contains(intersect(s2,s3), 3))
  
  println("Diff")
  println(contains(diff(s2,s2), 2))
  
  println(contains(diff(s2,s3), 2))
  println(contains(diff(s2,s3), 3))
  
  println("Filter")
  println(contains(filter(s2, x => x == 2), 2))
  
  val s23 = filter(union(s2, s3), x => x / 3 == 1)
  println(contains(s23, 3))
  
  println("Forall")
  println(forall(union(s2, union(s3, s4)), x => x > 1))
  println(forall(union(s3, s4), x => x > 2))
}
