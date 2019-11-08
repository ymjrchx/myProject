package com.atguigu.spark

import org.apache.spark.{SparkConf, SparkContext}
import org.slf4j.LoggerFactory

/**
  * Created by wuyufei on 31/07/2017.
  */
object WordCount {

  val logger = LoggerFactory.getLogger(WordCount.getClass)

  def main(args: Array[String]) {
    //创建SparkConf()并设置App名称
    val conf = new SparkConf().setMaster("local[3]").setAppName("WC")
    //创建SparkContext，该对象是提交spark App的入口
    val sc = new SparkContext(conf)

    //使用sc创建RDD并执行相应的transformation和action
    sc.textFile("F:\\BaiduNetdiskDownload\\尚硅谷2018大数据全套（8月8更新版）\\21_尚硅谷大数据技术之Spark-9.13-9.22\\Spark\\spark\\doc\\tbStock.txt").flatMap(_.split(" ")).map((_, 1)).reduceByKey(_+_, 1).sortBy(_._2, false).saveAsTextFile("outputDoc/wordcount")
    //停止sc，结束该任务

    

    logger.info("complete!")

    sc.stop()

  }

}








