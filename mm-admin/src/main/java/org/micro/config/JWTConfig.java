package org.micro.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author micro-paul
 * @date 2022年02月10日 9:25
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JWTConfig {

    /** Request Headers ： Authorization */
    private String header;

    /** 令牌前缀，最后留个空格 Bearer */
    private String tokenStartWith;

    /** 必须使用最少88位的Base64对该令牌进行编码 */
    private String base64Secret;
    private String secret;
    /** 令牌过期时间 此处单位/毫秒 */
    private Long tokenValidityInSeconds;

    /** 在线用户 key，根据 key 查询 redis 中在线用户的数据 */
    private String onlineKey;

    /** 验证码 key */
    private String codeKey;

    /** 不需要认证的请求地址 */
    public static String antMatchers;

    public String getTokenStartWith() {
        return tokenStartWith + " ";
    }

}
