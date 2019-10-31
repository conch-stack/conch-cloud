package ltd.beihu.core.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BeihuCloudEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeihuCloudEurekaApplication.class, args);
    }

}
