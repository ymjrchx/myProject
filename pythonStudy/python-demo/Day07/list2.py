"""
列表常用操作
- 列表连接
- 获取长度
- 遍历列表
- 列表切片
- 列表排序
- 列表反转
- 查找元素
Version: 0.1
Author: 骆昊
Date: 2018-03-06
"""

def main():
    fruits = ['grape', 'apple', 'strawberry', 'waxberry']
    fruits += ['pitaya', 'pear', 'mango']
    print(fruits)
    for fruit in fruits:
        print(fruit.title(),end=" ")
    print()
    fruits2 = fruits[1:4]
    print(fruits2)
    fruits3 = fruits[:]
    print(fruits3)
    fruits4 = fruits[-3:-1]
    print(fruits4)
    fruits5 = fruits[::-1]
    print(fruits5)
if __name__ == '__main__':
    main()