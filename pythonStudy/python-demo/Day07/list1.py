"""
定义和使用列表
- 用下标访问元素
- 添加元素
- 删除元素
Version: 0.1
Author: 骆昊
Date: 2018-03-06
"""

def main():
    fruits = ['grape', '@pple', 'strawberry', 'waxberry']
    print(fruits)
    print(fruits[0])
    print(fruits[1])
    print(fruits[-1])
    print(fruits[-2])
    fruits[1]='apple'
    print(fruits)
    fruits.append("pitaya")
    fruits.insert(0,"banana")
    print(fruits)
    del fruits[1]
    print(fruits)
    fruits.pop()
    print(fruits)
    fruits.pop(0)
    print(fruits)
    fruits.remove('apple')
    print(fruits)

if __name__ == '__main__':
    main()
