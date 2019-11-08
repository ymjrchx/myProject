package com.study

/**
  * @author chenxin
  * @date 2019/6/24 13:38
  */
class Marker private(val color:String){

  println("创建"+this)
  override def toString():String="颜色标记"+color
}

object Marker{
  private val markers:Map[String,Marker]=Map(
    "red"->new Marker("red"),
    "blue"->new Marker("blue"),
    "green"->new Marker("green")
  )
  def apply(color:String)={
    if(markers.contains(color)) markers(color) else null
  }
  def getMarker(color:String)={
    if(markers.contains(color)) markers(color) else null
  }
  def sayHello(name:String){println("hello, "+name)}

  def main(args: Array[String]): Unit = {
    val sayHelloFunc = sayHello _
    sayHelloFunc("leo")
    val arr = Range(1,7);
    arr.foreach(println)
    val res = arr.map(_*2)
    for(a <-res) println(a)
    println(res.sum)
    println(res.max)
    println(res.sorted.reverse)
    println(res)
    val sources =Map("tom"->80,"jerry"->99)
    println(sources("tom"))
    sources.map(println)


  }
}
