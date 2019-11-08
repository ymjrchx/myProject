"""
Python常用模块
- 运行时服务相关模块: copy / pickle / sys / ...
- 数学相关模块: decimal / math / random / ...
- 字符串处理模块: codecs / re / ...
- 文件处理相关模块: shutil / gzip / ...
- 操作系统服务相关模块: datetime / os / time / logging / io / ...
- 进程和线程相关模块: multiprocessing / threading / queue
- 网络应用相关模块: ftplib / http / smtplib / urllib / ...
- Web编程相关模块: cgi / webbrowser
- 数据处理和编码模块: base64 / csv / html.parser / json / xml / ...
Version: 0.1
Author: 骆昊
Date: 2018-03-05
"""

import time
import shutil
import os

seconds = time.time()
print(seconds)
localtime = time.localtime(seconds)
print(localtime)
print(localtime.tm_year)
print(localtime.tm_mon)
print(localtime.tm_mday)
asctime = time.asctime(localtime)
print(asctime)
mydate = time.strptime("2018-1-1","%Y-%m-%d")
print(mydate)

shutil.copy("D:\\dgg\\ymjrgithub\\python-demo\\Day06\\function3.py","D:\\dgg\\ymjrgithub\\python-demo\\Day06\\functiion33.py")
os.system("dir")
os.chdir("D:\dgg")
os.system("dir")
os.mkdir("testpython")

if __name__ == '__main__':
    pass