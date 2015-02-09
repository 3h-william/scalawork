package sort

import scala.collection.mutable.ArrayBuffer
import scala.util.Sorting

object SortTest {
  def main(args: Array[String]): Unit = {
    val b = ArrayBuffer(7, 22, 28, 4, 3, 9, 7, 5)
    val b1 = b.sortWith(_ > _)
    val b2 = b.sortWith(_ < _)
    println(b1)
    println(b2)

    // util sort
    val b3 = b.toArray
    Sorting.quickSort(b3)
    for (elem: Int <- b3) print(elem + ",")
    
    var c = new Array[Int](b.length)
    b.copyToArray(c)
    c.foreach { x => println(x) }

  }

}