package catalyst.parser.calc

/**
 * Created by wz68 on 2015/9/14.
 */
class CalcTest {

  import org.junit.Assert._
  import org.junit._

  @Test def ASTTest = {
    val n1 = Number(5)

    assertEquals(5, n1.value, 0)
  }

  @Test def equalityTest = {
    val binop = BinaryOp("+", Number(5), Number(10))

    assertEquals(Number(5), binop.left)
    assertEquals(Number(10), binop.right)
    assertEquals("+", binop.operator)
  }

  @Test def parseAnExpr1 =
    assertEquals(
      Number(5),
      Calc.parse("5")
    )
  @Test def parseAnExpr2 =
    assertEquals(
      Number(5),
      Calc.parse("(5)")
    )
  @Test def parseAnExpr3 =
    assertEquals(
      BinaryOp("+", Number(5), Number(5)),
      Calc.parse("5 + 5")
    )
  @Test def parseAnExpr4 =
    assertEquals(
      BinaryOp("+", Number(5), Number(5)),
      Calc.parse("(5 + 5)")
    )
  @Test def parseAnExpr5 =
    assertEquals(
      BinaryOp("+", BinaryOp("+", Number(5), Number(5)), Number(5)),
      Calc.parse("(5 + 5) + 5")
    )
  @Test def parseAnExpr6 =
    assertEquals(
      BinaryOp("+", BinaryOp("+", Number(5), Number(5)), BinaryOp("+", Number(5),
        Number(5))),
      Calc.parse("(5 + 5) + (5 + 5)")
    )
}