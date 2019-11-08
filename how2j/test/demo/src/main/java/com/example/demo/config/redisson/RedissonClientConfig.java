package com.example.demo.config.redisson;


import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

//@Configuration
public class RedissonClientConfig {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
  //  private RedissonNodes nodes;

    @Bean
    public RedissonClient initialRedissonClient(){
        String[] nodesstr = null;
                //nodes.getNodes().toArray(new String[nodes.getNodes().size()]);
        logger.info("redissonClient nodes:{}", Arrays.toString(nodesstr));
        Config config = new Config();
        config.useClusterServers()
            .addNodeAddress(nodesstr);
        return Redisson.create(config);
    }
}