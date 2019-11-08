package com.atguigu.spark

import org.apache.spark.{Partitioner, SparkConf, SparkContext}

/**
  * Created by wuyufei on 04/09/2017.
  */
class CustomerPartitioner(numParts:Int) extends Partitioner {

  //覆盖分区数
  override def numPartitions: Int = numParts

  //覆盖分区号获取函数
  override def getPartition(key: Any): Int = {
    val ckey: String = key.toString
    ckey.substring(ckey.length-1).toInt%numParts
  }
}

object
CustomerPartitioner {
  def main(args: Array[String]) {
    val conf=new SparkConf().setAppName("partitioner").setMaster("local")
    val sc=new SparkContext(conf)

    val data=sc.parallelize(List("aa.2","bb.2","cc.3","dd.3","ee.5"))

    data.map((_,1)).partitionBy(new CustomerPartitioner(5)).keys.saveAsTextFile("outputDoc/partitioner")

    sc.stop()
  }
}