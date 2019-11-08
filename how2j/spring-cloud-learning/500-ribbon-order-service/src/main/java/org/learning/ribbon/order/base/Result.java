package org.learning.ribbon.order.base;

import lombok.Data;

@Data
public class Result<T> {

    private int code;

    private String message;

    private T data;

    private Long serverTime = System.currentTimeMillis();

}
