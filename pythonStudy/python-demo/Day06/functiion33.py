def myfilter(mystr):
    return len(mystr) == 6

if __name__ == '__main__':
    print(chr(0x9a86))
    print(hex(ord('骆')))
    print(abs(-1.245))
    print(pow(1.12345,5))
    print(round(-1.2345))
    fruits = ['orange','peach','durian','watermelon']
    print(fruits[slice(1,3)])
    fruits2 = list(filter(myfilter, fruits))
    print(fruits)
    print(fruits2)
    print(slice(1,3))
