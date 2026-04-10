package vod.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "vod")
public class VodConfig {

    @Bean
    String foo() { return "bar"; }
}