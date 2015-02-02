
import scala.util.control.Breaks._

object Test2 {
  def main(args: Array[String]): Unit = {
    lazy val y = 1
    println(otherRecursive(1 to 3: _*))
    try {
      println(sqrt(-1))
    } catch {
      case t: Throwable => t.printStackTrace() // TODO: handle error
    } finally {
      println("finally");
    }

  }

  def sqrt(x: Int): Double = {
    if (x < 0) throw new RuntimeException("should not be nagetive")
    else math.sqrt(x)
  }

  def recursiveSum(args: Int*): Int = {
    if (args.length == 0) 0
    else args.head + recursiveSum(args.tail: _*)
  }

  def otherRecursive(args: Int*): Int = {
    if (args.length == 0) 1;
    else { println(args); args.head * otherRecursive(args.tail: _*) }
  }
}