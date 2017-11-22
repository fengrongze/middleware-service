package com.softisland.middleware.service.background.config;

import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by fengrongze on 2016/10/28.
 */
@EnableRedisHttpSession(maxInactiveIntervalInSeconds=3600)
public class SessionConfig {
}
