package catalyst.parser

import scala.util.parsing.combinator._

class SimpleParser extends RegexParsers {
  def word: Parser[String] = """[a-z]+""".r ^^ {
    _.toString
  }
    def word2: Parser[String] = """johnny""".r ^^ {
      _.toString
    }
}

object TestSimpleParser extends SimpleParser {
 // def main(args: Array[String]) = println(parse(word, "johnny come lately"))
 def main(args: Array[String]) {
//   val x = parse(word, "johnny come lately")
   //println(parse(word, "johnny come lately"))
   parse(word, "johnny come lately") match {
     case Success(matched,x) => {
       println(matched)
       println(x)
     }
     case Failure(msg,_) => println("FAILURE: " + msg)
     case Error(msg,_) => println("ERROR: " + msg)
   }
 }
}