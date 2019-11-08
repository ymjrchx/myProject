def main():
    fruits = ['grape', 'apple', 'strawberry', 'waxberry', 'pitaya']
    print(max(fruits))
    print(min(fruits))
    max_value = min_value=fruits[0]
    for elem in fruits:
        if elem >max_value:
            max_value=elem
        elif elem<min_value:
            min_value=elem
    print("Max:",max_value)
    print("Min:",min_value)
if __name__ == '__main__':
    main()

