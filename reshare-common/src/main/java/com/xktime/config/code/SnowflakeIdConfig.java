package com.xktime.config.code;

import com.xktime.utils.SnowflakeIdUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SnowflakeIdConfig {

    @Value("${code.snow-flake-id.workerId}")
    private int workerId;

    @Value("${code.snow-flake-id.dataCenterId}")
    private int dataCenterId;

    @Bean
    public SnowflakeIdUtil getSnowflakeIdUtil() {
        return new SnowflakeIdUtil(workerId, dataCenterId);
    }
}
