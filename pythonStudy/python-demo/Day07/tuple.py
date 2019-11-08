"""
元组的定义和使用
Version: 0.1
Author: 骆昊
Date: 2018-03-06
"""

def main():
    t = ('骆昊', 38, True, '四川成都')
    print(t)
    print(t[0])
    print(t[1])
    print(t[2])
    print(t[3])
    for member in t:
        print(member)
   # t[0]='王大富'
    t = ('王大锤', 20, True, '云南昆明')
    print(t)
    person = list(t)
    print(person)
    person[0] = '李小龙'
    person[1] = 25
    print(person)
    fruits_list=['apple','banna',"oarange"]
    fruits_tuple = tuple(fruits_list)
    print(fruits_tuple)
    print(fruits_tuple[1])
if __name__ == '__main__':
    main()