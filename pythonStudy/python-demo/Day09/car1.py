"""
属性的使用
- 访问器/修改器/删除器
- 使用__slots__对属性加以限制
Version: 0.1
Author: 骆昊
Date: 2018-03-12
"""

class Car(object):
    __slots__ = ('_brand',"_max_speed")
    def __init__(self,brand,max_speed):
        self._brand=brand
        self._max_speed=max_speed
    @property
    def brand(selfs):
        return selfs._brand

    @brand.setter
    def brand(self,brand):
        self._brand=brand
    @brand.deleter
    def brand(self):
        del self._brand
    @property
    def max_speed(self):
        return self._max_speed
    @max_speed.setter
    def max_speed(self,max_speed):
        if max_speed <0:
            raise ValueError('Invalid max speed for car')
        self._max_speed=max_speed
    def __str__(self):
        return 'Car: [品牌=%s, 最高时速=%d]' % (self._brand, self._max_speed)

if __name__ == '__main__':
    car=Car('QQ',120)
    print(car)
   # car.max_speed=-122
    car.max_speed=330
    car.brand='Benz'
   # car.current_speed=80
    print(car)
    del car.brand

    print(Car.brand)
    print(Car.brand.fget)
    print(Car.brand.fset)
    print(Car.brand.fdel)
