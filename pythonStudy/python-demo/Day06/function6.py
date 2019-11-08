def foo1():
    a=5
foo1()
b=10
def foo2():
    print(b)
foo2()
def foo3():
    b=100
    print(b)

foo3()
print(b)
def foo4():
    global b
    b = 300
    print(b)
foo4()
print(b)
if __name__ == '__main__':
    pass