package com.study

import java.io.{File, FileWriter}

import scala.util.Random

/**
  * @author chenxin
  * @date 2019/6/25 19:41
  */
object PeopleInfoFileGenerator {
  def main(args: Array[String]): Unit = {
    val writer = new FileWriter(new File("sample_people_info.txt"),false)
    val rand = new Random()
    for (i <- 1 to 10000){
      var height = rand.nextInt(220)
      if(height < 50){
        height+=50
      }
      var gender = getRandomGender
      if(height <100 && gender=="M"){
        height+=100
      }
      if(height<100 && gender=="F"){
        height+=50
      }
      writer.write(i+" "+gender +" "+ height)
      writer.write(System.getProperty("line.separator"))

    }
    writer.flush()
    writer.close()
    println("People Information file generated successfully")
  }

  def getRandomGender():String={
    val rand = new Random()
    val randNum = rand.nextInt(2)+1
    if(randNum %2 == 0){
      "M"
    }else{
      "F"
    }
  }






































}
