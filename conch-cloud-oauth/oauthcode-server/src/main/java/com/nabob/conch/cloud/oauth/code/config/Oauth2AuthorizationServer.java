package com.nabob.conch.cloud.oauth.code.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * 授权服务器
 *
 * @author Adam
 * @date 2020/4/4
 */
@Configuration
@EnableAuthorizationServer
public class Oauth2AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client-id")
                .secret("112233")
                .redirectUris("http://localhost:9001/callback")
                // 授权码模式
                .authorizedGrantTypes("authorization_code")
                // 简化模式
                // .authorizedGrantTypes("implicit")
                // 有效时间
                // .accessTokenValiditySeconds(120)
                .scopes("read_userinfo");
    }
}
