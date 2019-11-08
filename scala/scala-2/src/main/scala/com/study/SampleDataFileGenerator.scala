package com.study

import java.io.{File, FileWriter}
import java.util.Random

/**
  * @author chenxin
  * @date 2019/6/25 11:08
  */
object SampleDataFileGenerator {
  def main(args: Array[String]): Unit = {
    val writer = new FileWriter(new File("sample_age_data.txt"),false)
    val rand = new Random()
    for(i <- 1 to 1000000){
      writer.write(i+" "+rand.nextInt(100))
      writer.write(System.getProperty("line.separator"))
    }
    writer.flush()
    writer.close()
  }

}
