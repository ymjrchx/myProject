from pyspark import SparkConf
from pyspark import SparkContext

if __name__ == '__main__':
    conf = SparkConf().setMaster("local[1]").setAppName("worldcount")
    sc = SparkContext(conf=conf)
    lines = sc.textFile("../data/words")
    words = lines.flatMap(lambda line: line.split(","))
    pair_words = words.map(lambda word: (word, 1))
    result = pair_words.reduceByKey(lambda a, b: a + b)
    print(result.collect())
