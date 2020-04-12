package ltd.beihu.core.cloud.oauth.pwd.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 资源API 需授权获取
 *
 * @author Adam
 * @date 2020/4/4
 */
@RestController
public class UserInfoController {

    /**
     * 资源API - 获取用户信息
     *
     * @return UserInfo
     */
    @CrossOrigin
    @RequestMapping("/api/userinfo")
    public ResponseEntity<UserInfo> getUserInfo() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserInfo userInfo = new UserInfo();
        userInfo.setName(user.getUsername());
        userInfo.setEmail(user.getUsername() + "163.com");
        return ResponseEntity.ok(userInfo);
    }
}
