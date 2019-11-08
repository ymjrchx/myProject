"""
实现进程间的通信
Version: 0.1
Author: 骆昊
Date: 2018-03-20
"""

import multiprocessing
import os
def sub_task(queue):
    print("子进程号:",os.getpgid())

