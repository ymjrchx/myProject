str1 = "hello ,world!"
print("长度是:",len(str1))
print(str1.title())
print(str1.upper())
print(str1.isupper())
print(str1.startswith("hello"))
print(str1.endswith("hello"))
print(str1.startswith("!"))
print(str1.endswith("!"))
str2 = '- \u9a86\u660a'
str3 = str2.title()+" "+ str2.lower()
print(str3)
if __name__ == '__main__':
    pass