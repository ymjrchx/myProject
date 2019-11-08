"""
属性的使用
- 使用已有方法定义访问器/修改器/删除器
Version: 0.1
Author: 骆昊
Date: 2018-03-12
"""


class Car(object):
    def __init__(self, brand, max_speed):
        self.set_brand(brand)
        self.set_max_speed(max_speed)

    def get_brand(self):
        return self._brand

    def set_brand(self, brand):
        self._brand = brand

    def get_max_speed(self):
        return self._max_speed

    def set_max_speed(self, max_speed):
        if max_speed < 0:
            raise ValueError('Invalid max speed for car')
        self._max_speed = max_speed

    def __str__(self):
        return 'Car: [品牌=%s, 最高时速=%d]' % (self._brand, self._max_speed)
    brand = property(get_brand,set_brand)
    max_speed=property(get_max_speed,set_max_speed)
if __name__ == '__main__':
    car = Car('QQ',120)
    print(car)
    car.max_speed=320
    car.brand="Benz"
    print(car)
    print(Car.brand)
    print(Car.brand.fget)
    print(Car.brand.fset)