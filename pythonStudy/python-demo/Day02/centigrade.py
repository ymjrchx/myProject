"""
将华氏温度转换为摄氏温度
"""
f = float(input('请输入华氏温度: '))
c = (f - 32) / 1.8
print("%.1f华氏温度=%.1f摄氏温度" % (f, c))
if __name__ == '__main__':
    pass