package com.atguigu

object Collection {
  def main(args: Array[String]): Unit = {
    val tem = (1,2,"aa",1.3)
    println(tem._2)
    val symbols = Array("<","-",">")
    val counts = Array(1,20,2)
    val s = symbols.zip(counts)
    println(s(1))

    val q1 = new scala.collection.mutable.Queue[Int]
    q1 += 1
    q1+=2
    q1++=List(3,4)
    q1.dequeue()
    q1.enqueue(5,6,7)
    q1.head
    val list = List(1,7,2,9)
    println(list.reduce(_ - _))
    println(list.reduceLeft(_ - _))
    val iter1 = (1 to 10)
    println(iter1)








  }

}
