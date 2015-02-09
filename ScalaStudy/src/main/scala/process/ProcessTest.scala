package process

import scala.sys.process.ProcessBuilder
import scala.sys.process._


object ConversionTest {
  def main(args: Array[String]): Unit = {
//    var pb = new ProcessBuilder()
//    pb = pb.command("E:")
//    var process = pb.start()
//    println(process.getOutputStream.toString())
    
    val contents =Process("cmd dir").lines
    contents.foreach { x => println(x) }
    
    
    
  }
}