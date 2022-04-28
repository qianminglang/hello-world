package com.clear.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;
import org.apache.shardingsphere.spi.keygen.ShardingKeyGenerator;

import java.util.Properties;
@Slf4j
public class SelfGeneratorId implements ShardingKeyGenerator {
    private  SnowflakeShardingKeyGenerator snow = new SnowflakeShardingKeyGenerator();
    @Override
    public Comparable<?> generateKey() {
        log.info("使用雪花算法作为自己得id生成器");
        return snow.generateKey();
    }

    @Override
    public String getType() {
        return "LAGOUID";
    }

    @Override
    public Properties getProperties() {
        return null;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
