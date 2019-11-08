package com.example.demo.transaction.atomikos.mapper.master;

import com.example.demo.transaction.atomikos.domain.Demo;
import org.springframework.stereotype.Repository;

/**
 * @author chenxin
 * @date 2019/9/27 16:37
 */
@Repository
public interface MasterDemoMapper {
    int save(Demo demo);
}
