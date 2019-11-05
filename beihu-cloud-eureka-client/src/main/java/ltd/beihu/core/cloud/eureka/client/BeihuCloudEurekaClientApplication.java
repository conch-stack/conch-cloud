package ltd.beihu.core.cloud.eureka.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BeihuCloudEurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeihuCloudEurekaClientApplication.class, args);
    }

}
