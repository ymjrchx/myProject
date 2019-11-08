package org.learning.feign.order.dto;

import lombok.Data;

@Data
public class UserMoneyDto implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private String account;

    private Integer money;

}
