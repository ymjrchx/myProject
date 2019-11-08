"""
读写二进制文件
Version: 0.1
Author: 骆昊
Date: 2018-03-13
"""

import base64
with open('mm.jpg','rb') as f:
    data = f.read()
    print(type(data))
    print(data)
    print("字节数",len(data))
    print(base64.b64encode(data))
with  open('girl.jpg','wb') as f:
    f.write(data)
print("写入完成")
if __name__ == '__main__':
    pass
