import math

radius = float(input("请输入圆半径: "))
perimeter = 2 * math.pi * radius
area = math.pi * radius * radius
print("周长: %.2f" % perimeter)
print("面积: %.2f" % area)
if __name__ == '__main__':
    pass