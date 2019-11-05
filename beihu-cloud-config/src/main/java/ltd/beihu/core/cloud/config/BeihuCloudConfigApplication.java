package ltd.beihu.core.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class BeihuCloudConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeihuCloudConfigApplication.class, args);
    }

}
