package ltd.beihu.core.cloud.eureka.client.ctrl;

import ltd.beihu.core.cloud.eureka.client.service.FeignTestService;
import ltd.beihu.core.cloud.eureka.client.service.FileUploadFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EurekaClientConfigBean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/query")
public class QueryController {

    @Autowired
    private EurekaClientConfigBean eurekaClientConfigBean;
    @Autowired
    private FeignTestService feignTestService;
    @Autowired
    private FileUploadFeignService fileUploadFeignService;

    /**
     * 测试Feign简单请求
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

    /**
     * 测试Feign文件上传
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String imageUpload(MultipartFile file) throws Exception{
        return fileUploadFeignService.fileUpload(file);
    }

}