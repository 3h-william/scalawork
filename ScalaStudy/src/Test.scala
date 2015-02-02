
import scala.util.control.Breaks._

object Test {
  def main(args: Array[String]): Unit = {

    val s = "Hello"
    var sum = 0;
    for (i <- 0 until s.length()) {
      println(s(i))
      sum = sum + s(i);
    }
    println(sum)

    sum = 0
    breakable {
      for (c <- s) {
        sum = sum + c
        if (sum > 100) break;
      }
    }
    println(sum)

    var v = for (c <- "hello"; i <- 0 to 1) yield (c + i).toChar
    println(v)
    println(add(10))
    println(fac(4))
  }

  def add(x: Int): String = x + "2";
  def fac(n: Int): Int = if (n == 0) 1 else n * fac(n - 1)
}