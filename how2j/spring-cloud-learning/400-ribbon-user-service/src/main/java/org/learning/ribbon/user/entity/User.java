package org.learning.ribbon.user.entity;

import lombok.Data;

@Data
public class User implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String account;

    private Double money;


}
