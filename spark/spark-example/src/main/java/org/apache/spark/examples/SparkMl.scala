package org.apache.spark.examples

import org.apache.spark.ml.classification.LinearSVC
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{PCA, StandardScaler, VectorAssembler}
import org.apache.spark.sql.SparkSession

/**
  * @author chenxin
  * @date 2019/12/24 15:25
  */
object SparkMl {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("sparkMl")
      .master("local")
      .getOrCreate()
    //读取libsvm 个数数据  稀疏矩阵存储 label index1:value1 index2:value2 ...
    val data = spark.read.format("libsvm").load("src\\main\\resources\\sample_libsvm_data.txt")

    data.show()
    data.printSchema()

    //归一化
    val scaler = new StandardScaler()
      .setInputCol("features")
      .setOutputCol("scaledFeatures")
      .setWithMean(true)
      .setWithStd(true)
      .fit(data)

    val scaleddata = scaler.transform(data).select("label","scaledFeatures").toDF("label","features")

    scaleddata.printSchema()
    scaleddata.show()

    //PCA降维
    val pca = new PCA().setInputCol("features").setOutputCol("pcaFeatures").setK(5).fit(scaleddata)

    val pcaResult = pca.transform(scaleddata).select("label","pcaFeatures").toDF("label","features")

    // 将经过主成分分析的数据，按比例划分为训练数据和测试数据
    val Array(trainIngData, testData) = pcaResult.randomSplit(Array(0.7, 0.3), seed=20)

    // 创建SVC分类器(Estimator)

    val lsvc = new LinearSVC().setMaxIter(10).setRegParam(0.1)
    // 训练分类器，生成模型(Transformer)

    val lsvcModel = lsvc.fit(trainIngData)
    // 用训练好的模型，验证测试数据

    testData.printSchema()


    val a1 =  lsvcModel.transform(testData)
    a1.show()
    a1.printSchema()
    val res = a1.select("prediction","label")

    //计算精度

    val evaluator = new MulticlassClassificationEvaluator()
      .setLabelCol("label")
      .setPredictionCol("prediction")
      .setMetricName("accuracy")

    val accuracy = evaluator.evaluate(res)

    println(s"Accuracy = ${accuracy}")

    //将标签与主成分合为一列

    val assembler = new VectorAssembler()
      .setInputCols(Array("label","features"))
      .setOutputCol("assemble")

    val output = assembler.transform(pcaResult)
    // 输出csv格式的标签和主成分，便于可视化

    val ass = output.select(output("assemble").cast("string"))
    ass.write.mode("overwrite").csv("predictOutput.csv")
  }

}



































































