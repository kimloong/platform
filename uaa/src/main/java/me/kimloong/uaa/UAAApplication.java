package me.kimloong.uaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by Zhang JinLong(150429) on 2017-08-22.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class UAAApplication {

    public static void main(String[] args) {
        SpringApplication.run(UAAApplication.class, args);
    }
}
