package com.sb.config;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "com.sb")
@Import({MessageConfiguration.class})
public class AppConfig {

}
