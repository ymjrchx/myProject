

def init(): String = {
  println("call init()")
  return ""
}
init()


def noLazy() {
  val property = init();//没有使用lazy修饰
  println("after init()")
  println(property)
}

def lazyed() {
  lazy val property = init();//使用lazy修饰
  println("after init()")
  println(property)
}


noLazy()

lazyed()