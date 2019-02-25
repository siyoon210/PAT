package com.injucksung.injucksung;

import com.injucksung.injucksung.config.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({
        FileStorageProperties.class
})
@SpringBootApplication
public class InjucksungApplication {

    public static void main(String[] args) {
        SpringApplication.run(InjucksungApplication.class, args);
    }
}

