"""
异步I/O操作 - asyncio模块
Version: 0.1
Author: 骆昊
Date: 2018-03-21
"""

import asyncio

async def wget(host):
    print("wget %s...."%host)
    connect=asyncio.open_connection(host,80)
    reader,writer = await connect
    header = 'GET / HTTP/1.0\r\nHost: %s\r\n\r\n' % host
    writer.write(header.encode('utf-8'))
    await writer.drain()
    while True:
        line = await reader.readline()
        if line == b'\r\n':
            break
        print('%s header > %s' % (host, line.decode('utf-8').rstrip()))
    writer.close()

loop = asyncio.get_event_loop()
hosts_list = ['www.sina.com.cn', 'www.sohu.com', 'www.163.com']
tasks = [wget(host) for host in hosts_list]
loop.run_until_complete(asyncio.wait(tasks))
loop.close()
if __name__ == '__main__':
    pass