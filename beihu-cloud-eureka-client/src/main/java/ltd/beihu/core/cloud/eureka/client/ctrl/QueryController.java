package ltd.beihu.core.cloud.eureka.client.ctrl;

import ltd.beihu.core.cloud.eureka.client.service.FeignTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EurekaClientConfigBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/query")
public class QueryController {

    @Autowired
    private EurekaClientConfigBean eurekaClientConfigBean;
    @Autowired
    private FeignTestService feignTestService;

    /**
     * 测试Feign
     * http://localhost:8081/query/search/github?str=spring-cloud-dubbo
     */
    @GetMapping(value = "/search/github")
    public String searchGithubRepoByStr(@RequestParam("str") String queryStr) {
        return feignTestService.searchRepo(queryStr);
    }

    /**
     * 测试Eureka
     */
    @GetMapping("/eureka-server")
    public Object getEurekaServerUrl(){
        return eurekaClientConfigBean.getServiceUrl();
    }


}