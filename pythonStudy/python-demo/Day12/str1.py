"""
字符串常用操作
Version: 0.1
Author: 骆昊
Date: 2018-03-19
"""

import pyperclip

print("my brother\'s name is \'007\'")
print(r'my bother\'s name is \'007\'')

str = 'hello123world'
print('he' in str)
print('her' in str)

print(str.isalpha())
print(str.isalnum())
print(str.isdecimal())

print(str[0:5].isalpha())
print(str[5:8].isdecimal())

list = ['床前明月光', '疑是地上霜', '举头望明月', '低头思故乡']
print('-'.join(list))
sentence = 'You go your way I will go mine'
words_list=sentence.split()
print(words_list)
email="  jacke@125.com   "
print(email)
print(email.strip())
print(email.lstrip())
print(email.rstrip())
pyperclip.copy('老虎不发猫你当我病危呀')
# 从系统剪切板获得文本
print(pyperclip.paste())
if __name__ == '__main__':
    pass

