/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.spark.examples;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.util.AccumulatorV2;
import org.apache.spark.util.LongAccumulator;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public final class JavaWordCount {
  private static final Pattern SPACE = Pattern.compile("\u0001");

  public static void main(String[] args) throws Exception {

      args = new String[1];
      args[0] = "src\\main\\resources\\kv1.txt";
    if (args.length < 1) {
      System.err.println("Usage: JavaWordCount <file>");
      System.exit(1);
    }

    SparkSession spark = SparkSession
      .builder().master("local")
      .appName("JavaWordCount")
      .getOrCreate();

      JavaSparkContext javaSparkContext = new JavaSparkContext(spark.sparkContext());

      VectorAccumulatorV2 vectorAccumulatorV2 = new VectorAccumulatorV2();
      LongAccumulator myCounter = javaSparkContext.sc().longAccumulator("myCounter");
      javaSparkContext.sc().register(vectorAccumulatorV2,"text");

      Broadcast<int[]> broadcast = javaSparkContext.broadcast(new int[]{1, 2, 3, 4});

      JavaRDD<String> lines = spark.read().textFile(args[0]).javaRDD();


      JavaRDD<String> persist = lines.persist(StorageLevel.MEMORY_AND_DISK_2());
      JavaRDD<Integer> lineLengths = persist.map(new GetLength(myCounter, vectorAccumulatorV2));
    int totalLength = lineLengths.reduce(new Sum());
      System.out.println("总长度为： " +totalLength);



    JavaRDD<String> words = persist.flatMap(s -> Arrays.asList(SPACE.split(s)).iterator());

    JavaPairRDD<String, Integer> ones = words.mapToPair(s -> new Tuple2<>(s, 1));

    JavaPairRDD<String, Integer> counts = ones.reduceByKey((i1, i2) -> i1 + i2);

    List<Tuple2<String, Integer>> output = counts.collect();
    for (Tuple2<?,?> tuple : output) {
      System.out.println(tuple._1() + ": " + tuple._2());
    }
      for (int i : broadcast.value()) {
          System.out.println(i);

      }

      System.out.println("我的广播变量为: " + myCounter.value());
      System.out.println("我的广播变量text： " + vectorAccumulatorV2.value());

      TimeUnit.MINUTES.sleep(5);

  }


  public static class GetLength implements Function<String, Integer>{
      LongAccumulator myCounter;
      VectorAccumulatorV2 vectorAccumulatorV2;

      public GetLength(LongAccumulator myCounter, VectorAccumulatorV2 vectorAccumulatorV2) {
          this.myCounter = myCounter;
          this.vectorAccumulatorV2 = vectorAccumulatorV2;
      }

      @Override
      public Integer call(String s) throws Exception {
          myCounter.add(s.length());
          vectorAccumulatorV2.add(s);
          return s.length();

      }
  }

  public static class Sum implements Function2<Integer,Integer,Integer> {
      @Override
      public Integer call(Integer v1, Integer v2) throws Exception {
          return v1 + v2;
      }

  }

  public static class VectorAccumulatorV2 extends AccumulatorV2<String, String >{
      StringBuilder stringBuilder = new StringBuilder();

      @Override
      public boolean isZero() {
          return true;
      }

      @Override
      public AccumulatorV2<String, String> copy() {
          VectorAccumulatorV2 v = new VectorAccumulatorV2();
          v.stringBuilder = this.stringBuilder;
          return v ;

      }

      @Override
      public void reset() {
          stringBuilder = new StringBuilder();
      }

      @Override
      public void add(String v) {
          stringBuilder.append(v);
      }

      @Override
      public void merge(AccumulatorV2<String, String> other) {

          stringBuilder.append(other.value());

      }

      @Override
      public String value() {
          return stringBuilder.toString();
      }
  }




}







































