package array

object TestArray {
  def main(args: Array[String]): Unit = {

    val nums = new Array[Int](10);

    // multi dimensional array
    var matrix = Array.ofDim[Double](3, 4)
    println(matrix(0)(1))
    matrix.foreach { x: Array[Double] =>
      {
        for (i <- 0 until x.length) x(i) = i
      }
    }
  }
}