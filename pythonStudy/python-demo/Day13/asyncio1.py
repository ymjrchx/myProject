"""
异步I/O操作 - asyncio模块
Version: 0.1
Author: 骆昊
Date: 2018-03-21
"""

import asyncio
import threading

@asyncio.coroutine
def hello():
    print('%s:hello.world!'%threading.current_thread())
    yield from asyncio.sleep(2)
    print('%s: goodbye, world!' % threading.current_thread())

loop= asyncio.get_event_loop()
tasks = [hello(),hello()]
loop.run_until_complete(asyncio.wait(tasks))
print("game over!")
loop.close()

if __name__ == '__main__':
    pass