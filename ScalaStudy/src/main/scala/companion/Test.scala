import scala.util.control.Breaks._
import scala.collection.mutable.ArrayBuffer
package companion {
  object Test {
    def main(args: Array[String]): Unit = {
      println("test") 

      var myIntArray = new Array[Int](10);
      var myStringArrya = new Array[String](10)

      for (i <- myIntArray) {
        println(i)
      }
      myIntArray.foreach { i: Int => println(i + 1) }

      var s = Array("a", "b"); // apply

      var dynamicArray = new ArrayBuffer[Int]
      dynamicArray += 5
      dynamicArray ++= Array(1,2,3,4)
      dynamicArray.insert(2,10)
      var b = new StringBuilder;
      dynamicArray.addString(b)
      val arr =dynamicArray.toArray;
      
      var mylist = List(("a","b"),("a1","b1"))
      var c = mylist.toMap
      println(c)
    }
  }
}