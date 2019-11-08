"""
读取CSV文件
Version: 0.1
Author: 骆昊
Date: 2018-03-13
"""
import csv
filename='example.csv'
try:
    with open(filename) as f:
        reader = csv.reader(f)
        data = list(reader)
except FileNotFoundError:
    print("无法打开文件",filename)
else:
    for itme in data:
        print("%-30s%-20s%-10s"%(itme[0],itme[1],itme[2]))

if __name__ == '__main__':
    pass