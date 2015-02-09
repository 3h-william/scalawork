package companion {

  object TestCompanion extends App {
    var z: MyCompanion = MyCompanion("hello")
    z.printF("william")
  }

  class MyCompanion(var regualStr: String) {
    def printF(str: String) {
      println(regualStr + ":" + str)
    }
  }

  object MyCompanion {
    def apply(regular: String): MyCompanion = {
      new MyCompanion(regular)
    }

  }
}
