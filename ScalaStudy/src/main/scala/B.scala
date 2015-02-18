
import scala.math
import scala.reflect.internal.util.StringOps
class B {

}

object B {
  def main(args: Array[String]): Unit = {
    val list = List(1,2,3)
    list.foreach { x => isEven(x) }
    isDivisibleBy(2)(11)
    val a =1;
    val b=2
    a + b
  }

val isEven: Int => Boolean = i => i % 2 == 0
  def isDivisibleBy(k: Int): Int => Boolean = {
    println("evaluating isDivisibleBy")
    i => i % k == 0
  }
}