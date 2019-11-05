package ltd.beihu.core.cloud.eureka.client.service;

import ltd.beihu.core.cloud.eureka.client.config.FeignMultipartSupportConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传
 */
@FeignClient(value = "feign-file-server", configuration = FeignMultipartSupportConfig.class)
public interface FileUploadFeignService {

    /***
     * 1.produces,consumes必填
     * 2.注意区分@RequestPart和RequestParam，不要将
     *      @RequestPart(value = "file") 写成@RequestParam(value = "file")
     */
    @RequestMapping(method = RequestMethod.POST, value = "/uploadFile/server",
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},  // 返回值类型
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)      // 处理请求的类型
    public String fileUpload(@RequestPart(value = "file") MultipartFile file);

}