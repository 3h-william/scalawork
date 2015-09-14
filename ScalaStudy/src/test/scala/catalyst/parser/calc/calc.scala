package catalyst.parser.calc

private[calc] abstract class Expr

private[calc] case class Number(value: Double) extends Expr

private[calc] case class UnaryOp(operator: String, arg: Expr) extends Expr

private[calc] case class BinaryOp(operator: String, left: Expr, right: Expr) extends Expr

object Calc {
  //  def evaluate(e : Expr) : Double =
  //  {
  //    e match {
  //      case Number(x) => x
  //      case UnaryOp("-", x) => -(evaluate(x))
  //      case BinaryOp("+", x1, x2) => (evaluate(x1) + evaluate(x2))
  //      case BinaryOp("-", x1, x2) => (evaluate(x1) - evaluate(x2))
  //      case BinaryOp("*", x1, x2) => (evaluate(x1) * evaluate(x2))
  //      case BinaryOp("/", x1, x2) => (evaluate(x1) / evaluate(x2))
  //    }
  //  }

  def evaluate(e: Expr): Double = {
    simplify(e) match {
      case Number(x) => x
      case UnaryOp("-", x) => -(evaluate(x))
      case BinaryOp("+", x1, x2) => (evaluate(x1) + evaluate(x2))
      case BinaryOp("-", x1, x2) => (evaluate(x1) - evaluate(x2))
      case BinaryOp("*", x1, x2) => (evaluate(x1) * evaluate(x2))
      case BinaryOp("/", x1, x2) => (evaluate(x1) / evaluate(x2))
    }
  }

  def evaluate(text : String) : Double = evaluate(parse(text))

  //  def simplify(e : Expr) : Expr =
  //  {
  //    e match {
  //      // Double negation returns the original value
  //      case UnaryOp("-", UnaryOp("-", x)) => x
  //      // Positive returns the original value
  //      case UnaryOp("+", x) => x
  //      // Multiplying x by 1 returns the original value
  //      case BinaryOp("*", x, Number(1)) => x
  //      // Multiplying 1 by x returns the original value
  //      case BinaryOp("*", Number(1), x) => x
  //      // Multiplying x by 0 returns zero
  //      case BinaryOp("*", x, Number(0)) => Number(0)
  //      // Multiplying 0 by x returns zero
  //      case BinaryOp("*", Number(0), x) => Number(0)
  //      // Dividing x by 1 returns the original value
  //      case BinaryOp("/", x, Number(1)) => x
  //      // Adding x to 0 returns the original value
  //      case BinaryOp("+", x, Number(0)) => x
  //      // Adding 0 to x returns the original value
  //      case BinaryOp("+", Number(0), x) => x
  //      // Anything else cannot (yet) be simplified
  //      case _ => e
  //    }
  //  }

  /*
     * Lex's version:
     */
  def simplify(e: Expr): Expr = {
    // first simplify the subexpressions
    val simpSubs = e match {
      // Ask each side to simplify
      case BinaryOp(op, left, right) => BinaryOp(op, simplify(left), simplify(right))
      // Ask the operand to simplify
      case UnaryOp(op, operand) => UnaryOp(op, simplify(operand))
      // Anything else doesn't have complexity (no operands to simplify)
      case _ => e
    }

    // now simplify at the top, assuming the components are already simplified
    def simplifyTop(x: Expr) = x match {
      // Double negation returns the original value
      case UnaryOp("-", UnaryOp("-", x)) => x

      // Positive returns the original value
      case UnaryOp("+", x) => x

      // Multiplying x by 1 returns the original value
      case BinaryOp("*", x, Number(1)) => x

      // Multiplying 1 by x returns the original value
      case BinaryOp("*", Number(1), x) => x

      // Multiplying x by 0 returns zero
      case BinaryOp("*", x, Number(0)) => Number(0)

      // Multiplying 0 by x returns zero
      case BinaryOp("*", Number(0), x) => Number(0)

      // Dividing x by 1 returns the original value
      case BinaryOp("/", x, Number(1)) => x

      // Dividing x by x returns 1
      case BinaryOp("/", x1, x2) if x1 == x2 => Number(1)

      // Adding x to 0 returns the original value
      case BinaryOp("+", x, Number(0)) => x

      // Adding 0 to x returns the original value
      case BinaryOp("+", Number(0), x) => x

      // Anything else cannot (yet) be simplified
      case e => e
    }
    simplifyTop(simpSubs)
  }


  // ... 解析器

  import scala.util.parsing.combinator._

  object OldAnyParser extends JavaTokenParsers
  {
    def expr: Parser[Any] = term ~ rep("+"~term | "-"~term)
    def term : Parser[Any] = factor ~ rep("*"~factor | "/"~factor)
    def factor : Parser[Any] = floatingPointNumber | "("~expr~")"

    def parse(text : String) =
    {
      parseAll(expr, text)
    }
  }
  object AnyParser extends JavaTokenParsers
  {
    def expr: Parser[Any] = (term~"+"~term) | (term~"-"~term) | term
    def term : Parser[Any] = (factor~"*"~factor) | (factor~"/"~factor) | factor
    def factor : Parser[Any] = "(" ~> expr <~ ")" | floatingPointNumber

    def parse(text : String) =
    {
      parseAll(expr, text)
    }
  }

  object ExprParser extends JavaTokenParsers
  {
    def expr: Parser[Expr] =
      (term ~ "+" ~ term) ^^ { case lhs~plus~rhs => BinaryOp("+", lhs, rhs) } |
        (term ~ "-" ~ term) ^^ { case lhs~minus~rhs => BinaryOp("-", lhs, rhs) } |
        term

    def term: Parser[Expr] =
      (factor ~ "*" ~ factor) ^^ { case lhs~times~rhs => BinaryOp("*", lhs, rhs) } |
        (factor ~ "/" ~ factor) ^^ { case lhs~div~rhs => BinaryOp("/", lhs, rhs) } |
        factor

    def factor : Parser[Expr] =
      "(" ~> expr <~ ")" |
        floatingPointNumber ^^ {x => Number(x.toFloat) }

    def parse(text : String) = parseAll(expr, text)
  }

  def parse(text : String) =
    ExprParser.parse(text).get

  def main(args: Array[String]) {
 /*   //val binop = BinaryOp("+", Number(5), Number(10))
    val unaryOp = UnaryOp("-", Number(5))
    //println(binop)
    val unaryOp2 = UnaryOp("-", unaryOp)
    println(evaluate(unaryOp2))

   val x = OldAnyParser.parse("1+2*3")
    println(x)*/
    print(evaluate("1+2"))
  }
}

