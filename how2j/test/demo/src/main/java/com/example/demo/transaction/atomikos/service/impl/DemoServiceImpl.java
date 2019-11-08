package com.example.demo.transaction.atomikos.service.impl;

import com.example.demo.transaction.atomikos.domain.Demo;
import com.example.demo.transaction.atomikos.mapper.business.BusinessDemoMapper;
import com.example.demo.transaction.atomikos.mapper.master.MasterDemoMapper;
import com.example.demo.transaction.atomikos.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author chenxin
 * @date 2019/9/27 16:34
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DemoServiceImpl implements DemoService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private MasterDemoMapper masterDemoMapper;

    @Resource
    private BusinessDemoMapper businessDemoMapper;

    /**
     * 正常测试分布式事务
     *
     * @return
     * @throws Exception
     */
    @Override
    public int save1() throws Exception {
        log.info("save");
        Demo dsDemo = new Demo();
        dsDemo.setName("xa事务测试");
        int row = masterDemoMapper.save(dsDemo);
        log.info("保存之后");
        Demo dsDemo1 = new Demo();
        dsDemo1.setName("xa事务测试2");
        int row2 = businessDemoMapper.save(dsDemo1);
        return row + row2;
    }

    /**
     * 测试分布式事务回滚
     * @return
     * @throws Exception
     */
    @Override
    public int save2() throws Exception {
        log.info("save2");
        Demo dsDemo = new Demo();
        dsDemo.setName("xa事务回滚测试");
        int row = masterDemoMapper.save(dsDemo);
        log.info("保存之后异常");
        int a = 1 / 0;

        Demo dsDemo1 = new Demo();
        dsDemo1.setName("xa事务回滚测试2");
        int row2 = businessDemoMapper.save(dsDemo1);
        return row + row2;
    }
}