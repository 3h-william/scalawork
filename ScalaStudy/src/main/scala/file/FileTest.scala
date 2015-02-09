package file

import scala.io.Source

object FileTest {

  def main(args: Array[String]): Unit = {

    val source = Source.fromFile("E:/tmp/a.txt", "UTF-8")
    val lineIterator = source.getLines()
    for (l <- lineIterator) {
      println(l)
    }
  }
}