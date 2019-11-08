"""
双色球随机选号程序
Version: 0.1
Author: 骆昊
Date: 2018-03-06
"""

from random import randrange, randint, sample


def display(balls):
    for index, ball in enumerate(balls):
        if index == len(balls) - 1:
            print("!", end=" ")
        print("%02d" % ball, end=" ")
    print()


def random_select():
    red_balls = [x for x in range(1, 34)]
    selected_balls = []
    for _ in range(6):
        index = randrange(len(red_balls))
        selected_balls.append(red_balls[index])
        del red_balls[index]
    # selected_balls=sample(red_balls,6)
    selected_balls.sort()
    selected_balls.append(randint(1, 16))
    return selected_balls


def main():
    n = int(input("机选几注: "))
    for _ in range(n):
        display(random_select())


if __name__ == '__main__':
    main()
