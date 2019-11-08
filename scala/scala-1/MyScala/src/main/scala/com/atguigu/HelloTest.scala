package com.atguigu

import scala.collection.mutable.ArrayBuffer
import scala.util.control.Breaks._
import scala.collection.JavaConverters._
import scala.collection.mutable

object HelloTest {
  def decorate(str: String,left: String="<<",right:String=">>")=left+str+right
  def main(args: Array[String]): Unit = {
    println("hello world")

    val a:Int = 1
    val x = if(a>0) 10 else -1;
    println(x)

    var y = if(a<0) 11 else Unit
    var yy:Any = if(a<0) 12
    println(yy)

    breakable{
      var i = 3
      while (true){
        println(i)
        i -=1
        if(i<0) break

      }

    }

    for(i<-1 to 3;j<-1 to 4 if i!=j){
      print(i*10 + j +" ")
    }

    val a1 = for (i <-1 to 10) yield i%3
    println(a1)

    println(decorate("hello"))
    println(decorate(left = "{",str = "www"))

    val array = Array(1,3,5,"ddd");
    for(i <- 0 until  array.length)println(array(i))
    println(array(3))
    val arrayBuffer = ArrayBuffer[Int](1,3,3)
    println(arrayBuffer)
    arrayBuffer.appendAll(arrayBuffer);
    println(arrayBuffer)
    arrayBuffer.copyToArray(array);
    println(array(2))
    val matrix = Array.ofDim[Int](3,4);
    matrix(2)(3)=12
    println(matrix)
    for(row <- matrix)
      for(ele <- row){
        println(ele)
      }

    val command = ArrayBuffer("ls", "-al", "/")
    // ProessBuilder是java方法
    val pb = new ProcessBuilder(command.asJava) // Scala to Java

    val cmd : mutable.Buffer[String] = pb.command().asScala // Java to Scala

    cmd == command
    val sores = scala.collection.mutable.Map("a"->1,"bb"->2)
    val sore22 = sores.getOrElse("bbb",3)
    println(sore22)

    val sore3 = sores.get("bb");
    println(sore3.get)


  }

}
