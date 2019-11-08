package com.study

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author chenxin
  * @date 2019/6/25 19:48
  */
object PeopleInfoCalculator {
  def main(args: Array[String]): Unit = {
    /*val conf = new SparkConf().setAppName("Spark Exercise info(Gender & Height) Calculator").setMaster("local")
    val sc = new SparkContext(conf)*/

    if (args.length < 1){
      println("Usage:PeopleInfoCalculator datafile")
      System.exit(1)
    }
    val conf = new SparkConf().setAppName("Spark Exercise:People Info(Gender & Height) Calculator")
    val sc = new SparkContext(conf)
    val dataFile = sc.textFile(args(0), 5)
    val maleDate = dataFile.filter(line=>line.contains("M")).map(line =>(line.split(" "))(1) +" "+line.split(" ")(2))
    val femaleData = dataFile.filter(line => line.contains("F")).map(
      line => (line.split(" ")(1) + " " + line.split(" ")(2)))

    maleDate.collect().foreach(x =>println(x))
    val maleHeightData = maleDate.map(line=>line.split(" ")(1).toInt)
    val femalHeightData = femaleData.map(line =>line.split(" ")(1).toInt)
    val hightesMale = maleHeightData.sortBy(x=>x,false).first()
    val hightesFemal = femalHeightData.sortBy(x=>x,false).first()
    println("Number of Male Peole:" + maleDate.count())
    println("Number of Female Peole:" + femaleData.count())

    println("Highest Male:" + hightesMale)
    println("Highest Female:" + hightesFemal)


  }
















































}
