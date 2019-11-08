package com.study

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author chenxin
  * @date 2019/6/25 11:12
  */
object AvgAgeCalculator {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Spark.Exercise.Average.Age.Calculator").setMaster("local")
    val sc = new SparkContext(conf)
    val dataFile = sc.textFile("sample_age_data.txt")
    val count = dataFile.count()
    val ageDate = dataFile.map(line=>line.split(" ")(1))
    val totalAge = ageDate.map(age=>Integer.parseInt(String.valueOf(age))).collect().reduce((a,b) =>a+b)
    println("Total Age: "+totalAge +"; Number of Peaple"+count)
    val ageAge: Double = totalAge.toDouble/count.toDouble
    println("Average age is  "+ageAge)
  }
}
