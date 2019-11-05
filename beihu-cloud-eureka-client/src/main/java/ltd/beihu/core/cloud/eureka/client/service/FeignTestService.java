package ltd.beihu.core.cloud.eureka.client.service;

import ltd.beihu.core.cloud.eureka.client.config.HelloFeignServiceConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: zjz
 * @Desc: 查询github上的厂库信息
 * @Date: 2019/11/5
 * @Version: V1.0.0
 */
@FeignClient(name = "feign-test", url = "https://api.github.com", configuration = HelloFeignServiceConfig.class)
public interface FeignTestService {

    @RequestMapping(value = "/search/repositories", method = RequestMethod.GET)
    String searchRepo(@RequestParam("q") String queryStr);
}
