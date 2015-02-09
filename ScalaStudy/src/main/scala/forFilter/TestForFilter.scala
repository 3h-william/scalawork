package forFilter

import scala.collection.mutable.ArrayBuffer

object TestForFilter {
  def main(args: Array[String]): Unit = {
    println("test for filter")
    val a1 = for (i <- 0 to 10) yield i
    val a2 = a1.filter { x => x % 2 == 0 }.map { x => x * 2 }
    println(a2)

    val b = new ArrayBuffer[Int]

    for (i <- 0 to 10) {
      b += i
    }
    println(b)

    // var b1:Array[Int] = b.toArray
    val b1 = b.filter(_ % 2 == 0).map(_ * 2)
    println(b1)

    multiArgs2(1, 2, 3, 4)
  }

  def multiArgs(x: Int*) = { x.foreach { x => println(2 * x) } }
  def multiArgs2(x: Int*) = {
    val it = x.iterator
    while (it.hasNext)
      print(it.next())
  }
}