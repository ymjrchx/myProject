package com.study

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author chenxin
  * @date 2019/6/25 9:54
  */
object WordCount {
  def main(args: Array[String]): Unit = {
    val inputFile="spark.txt"
    val outputFile = "outspark"
    val conf = new SparkConf().setAppName("WordCount").setMaster("local")
    val sc = new SparkContext(conf)
    val textFile = sc.textFile(inputFile)
    val wordCount=textFile.flatMap(line=>line.split(" ")).map(word =>(word,1)).reduceByKey((a,b) =>a+b)
    wordCount.foreach(println)
    wordCount.saveAsTextFile(outputFile)

  }

}
