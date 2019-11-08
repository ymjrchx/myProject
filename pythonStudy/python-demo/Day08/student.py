"""
定义和使用学生类
Version: 0.1
Author: 骆昊
Date: 2018-03-08
"""

def _foo():
    print("test")
class Student(object):
    def __init__(self, name, age):
        self.name = name
        self.age = age
    def study(self, course_name):
        print('%s正在学习%s.' % (self.name, course_name))

    def watch_av(self):
        if self.age < 18:
            print('%s只能观看《熊出没》.' % self.name)
        else:
            print('%s正在观看岛国大电影.' % self.name)

def main():
    stu1 = Student('骆昊', 38)
    stu1.study('Python程序设计')
    stu1.watch_av()
    stu2 = Student('王大锤', 15)
    stu2.study('思想品德')
    stu2.watch_av()
    print(stu1.age)
    print(stu1.name)


if __name__ == '__main__':
    main()