package com.study

/**
  * @author chenxin
  * @date 2019/6/24 11:29
  */
class Test {
  def prints(): Unit = {
    val a = 10
    val b = 20
    val c = 25
    val d = 25
    println("a + b = " + (a + b))
    println("a - b = " + (a - b))
    println("a * b = " + (a * b))
    println("b / a = " + (b / a))
    println("b % a = " + (b % a))
    println("c % a = " + (c % a))
  }

}
object Test{

    def main(args: Array[String]) {
      val it = Iterator("Baidu", "Google", "Runoob", "Taobao")

      while (it.hasNext){
        println(it.next())
      }
    }
}
