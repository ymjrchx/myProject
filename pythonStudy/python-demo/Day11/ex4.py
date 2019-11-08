"""
引发异常和异常栈
Version: 0.1
Author: 骆昊
Date: 2018-03-13
"""
import traceback
import logging
import  time

def f1():
    raise AssertionError("发生异常")

def f2():
    f1()
def f3():
    f2()

if __name__ == '__main__':
    try:
        f3()
    except BaseException as e:
        logging.exception(e)
        time.sleep(1)
        msg = traceback.format_exc()
        print(msg)
