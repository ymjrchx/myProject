package org.apache.spark.examples;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;

import java.util.Arrays;

/**
 * @author chenxin
 * @date 2019/12/18 15:48
 */
public class DateFrameDemo {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf();
        conf.setMaster("local").setAppName("jsonRDD");
        JavaSparkContext sc = new JavaSparkContext(conf);
        SQLContext sqlContext = new SQLContext(sc);
        JavaRDD<String> nameRDD = sc.parallelize(Arrays.asList(
                "{\"name\":\"zhangsan\",\"age\":\"18\"}",
                "{\"name\":\"lisi\",\"age\":\"19\"}",
                "{\"name\":\"wangwu\",\"age\":\"20\"}"
        ));
        JavaRDD<String> scoreRDD = sc.parallelize(Arrays.asList(
                "{\"name\":\"zhangsan\",\"score\":\"100\"}",
                "{\"name\":\"lisi\",\"score\":\"200\"}",
                "{\"name\":\"wangwu\",\"score\":\"300\"}"
        ));

        Dataset<Row> namedf = sqlContext.read().json(nameRDD);
        Dataset<Row> scoredf = sqlContext.read().json(scoreRDD);
        namedf.registerTempTable("name");
        scoredf.registerTempTable("score");

        Dataset<Row> result = sqlContext.sql("select name.name,name.age,score.score from name,score where name.name = score.name");
        result.show();
        result.printSchema();

        sc.stop();
    }
}
