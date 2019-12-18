package org.apache.spark.examples;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;

/**
 * @author chenxin
 * 测试验证pipeline计算模式
 * @date 2019/12/18 14:17
 */
public class PipeLineDemo {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf();
        conf.setMaster("local[*]").setAppName("pipeline");
        JavaSparkContext sc = new JavaSparkContext(conf);
        System.out.println(sc.defaultParallelism());
        System.out.println(sc.defaultMinPartitions());
            JavaRDD<Integer> rdd = sc.parallelize(Arrays.asList(1, 2, 3, 4,5));
        System.out.println(rdd.getNumPartitions());
        JavaRDD<Integer> rdd1 = rdd.map ( x -> {
            System.out.println(("map--------"+x));
              return x;
        });
        JavaRDD<Integer> rdd2 = rdd1.filter( x -> {
            System.out.println("fliter********"+x);
            return true;
        });
        rdd2.collect();
        sc.stop();
    }
}
