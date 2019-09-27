package com.story.storyadmin.config.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "weather-config")
public class WeatherProperties {
    String appId;
    String appSecret;
}
