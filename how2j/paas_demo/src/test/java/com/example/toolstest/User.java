package com.example.toolstest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author: dgg-linhongda
 * @Date: 2019/10/23 0023
 * @Description:
 */
@Getter
@Setter
@NoArgsConstructor
public class User {
    private String name;
    private String phoneNumber;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
