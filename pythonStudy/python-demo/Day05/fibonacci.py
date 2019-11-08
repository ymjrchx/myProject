a = 0
b = 1
for _ in range(20):
    (a, b) = (b, a + b)
    print(a, end=' ')

if __name__ == '__main__':
    pass