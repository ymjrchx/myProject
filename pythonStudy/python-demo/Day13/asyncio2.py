"""
异步I/O操作 - async和await
Version: 0.1
Author: 骆昊
Date: 2018-03-21
"""

import asyncio
import threading

async def hello():
    print('%s: hello, world!' % threading.current_thread())
    await asyncio.sleep(2)
    print('%s: goodbye, world!' % threading.current_thread())
loop = asyncio.get_event_loop()
tasks = [hello(),hello()]
loop.run_until_complete(asyncio.wait(tasks))
loop.close()
if __name__ == '__main__':
    pass


























