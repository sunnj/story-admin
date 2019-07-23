package com.story.storyadmin.config.shiro;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "permission-config")
public class ShiroFilterProperties {
    List<Map<String, String>> perms;
}
