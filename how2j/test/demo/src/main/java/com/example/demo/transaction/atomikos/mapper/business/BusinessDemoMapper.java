package com.example.demo.transaction.atomikos.mapper.business;

import com.example.demo.transaction.atomikos.domain.Demo;
import org.springframework.stereotype.Repository;

/**
 * @author chenxin
 * @date 2019/9/27 16:37
 */
@Repository
public interface BusinessDemoMapper {
    int save(Demo demo);
}

