package tuple

import java.util.TreeMap
import scala.collection.JavaConversions.mapAsScalaMap

object TupleTest {
  def main(args: Array[String]): Unit = {

    val scores = Map("Alice" -> 10, "Bob" -> 3, "ddd" -> 4)
    val scores2 = new scala.collection.mutable.LinkedHashMap[Int,String]
    var dmap:scala.collection.mutable.Map[String,Int] = new TreeMap[String,Int]
    for (i <- 1 to 100) {
      scores2 += (i->"i")
    }
    for ((k, v) <- scores2) {
      println(k)
    }
  
    val symbols = Array("<","-",">")
    val counts = List(2,10,2)
    val pairs= symbols.zip(counts)
    
    pairs.foreach(f=>println(f))
        
  }
}