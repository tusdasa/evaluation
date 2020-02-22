package net.tusdasa.evaluation.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.tusdasa.evaluation.commons.Token;
import org.apache.commons.codec.binary.Base64;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT认证工具类
 *
 * @author tusdasa
 * @version 1.0
 */

@Slf4j
public class JWTUtils {

    /**
     * 未拉到云端配置密钥时使用
     * */
    private final String SECRET_KEY = "9O2B+amfn52nzowCysmC7+hfuA0q9xvxcgLU1UDVYwM=";

    /**
     * 加密算法
     * */
    private final String MAC_NAME = "HMacSHA256";

    //private KeyGenerator keyGenerator;

    //private SecretKey secretKey;

    /**
     * 算法类
     * */
    private Algorithm algorithm;

    /**
     * JWT token 校验
     * */
    private Verification verification;

    /**
     * JWT token 校验
     * */
    private JWTVerifier jwtVerifier;


    public JWTUtils() {
    }

    /**
     * 构造 Algorithm
     * @param secret 加密密钥
     * */
    public JWTUtils(String secret) {
        // this.keyGenerator = KeyGenerator.getInstance(MAC_NAME);
        // this.secretKey = keyGenerator.generateKey();
        // this.algorithm = Algorithm.HMAC256(secretKey.getEncoded());
        if (secret != null) {
            this.algorithm = Algorithm.HMAC256(secret.getBytes());
        } else {
            this.algorithm = Algorithm.HMAC256(SECRET_KEY.getBytes());
        }
        System.out.println("secret: " + secret);
        this.verification = JWT.require(algorithm);
        this.jwtVerifier = verification.build();
    }

    /**
     * 根据身份和角色生成Token
     * @param ID 身份ID
     * @param role 角色
     * */
    public String generateNewToken(Long ID, Integer role) {
        JWTCreator.Builder builder = JWT.create();
        builder.withSubject(String.valueOf(ID));
        builder.withClaim("role", role);
        builder.withExpiresAt(new Date(System.currentTimeMillis() + (3600 * 1000)));
        return builder.sign(algorithm);
    }
    /**
     * JWT 校验
     * @param token jwt token
     * @return
     * <ul>
     *     <li>
     *         校验成功
     *         <pre>
     *             "code":200,
     *             "token":Token 实例son
     *         </pre>
     *
     *     </li>
     *     <li>
     *         校验失败
     *         <pre>
     *             code":500,
     *         </pre>
     *     </li>
     * </ul>
     * */
    public Map<String, Object> check(String token) {
        Map<String, Object> result = new HashMap<>();
        try {
            DecodedJWT decodedJWT = this.jwtVerifier.verify(token);
            String object = new String(Base64.decodeBase64(decodedJWT.getPayload()), "UTF8");
            result.put("code", 200);
            ObjectMapper objectMapper = new ObjectMapper();
            result.put("token", objectMapper.readValue(object, Token.class));
        } catch (Exception e) {
            result.put("code", 500);
        }
        return result;

    }
}
