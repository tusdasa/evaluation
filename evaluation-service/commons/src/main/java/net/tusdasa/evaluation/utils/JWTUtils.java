package net.tusdasa.evaluation.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.tusdasa.evaluation.commons.Token;
import org.apache.commons.codec.binary.Base64;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {

    private final String SECRET_KEY = "9O2B+amfn52nzowCysmC7+hfuA0q9xvxcgLU1UDVYwM=";

    private final String MAC_NAME = "HMacSHA256";

    //private KeyGenerator keyGenerator;

    //private SecretKey secretKey;

    private Algorithm algorithm;

    private Verification verification;

    private JWTVerifier jwtVerifier;


    public JWTUtils() {
    }

    public JWTUtils(String secret) {
        // this.keyGenerator = KeyGenerator.getInstance(MAC_NAME);
        // this.secretKey = keyGenerator.generateKey();
        // this.algorithm = Algorithm.HMAC256(secretKey.getEncoded());
        if (secret != null && !secret.isEmpty()) {
            this.algorithm = Algorithm.HMAC256(secret.getBytes());
        } else {
            this.algorithm = Algorithm.HMAC256(SECRET_KEY.getBytes());
        }

        this.verification = JWT.require(algorithm);
        this.jwtVerifier = verification.build();


    }

    public String generateNewToken(Long ID, Integer role) {
        JWTCreator.Builder builder = JWT.create();
        builder.withSubject(String.valueOf(ID));
        //builder.withClaim("uuid", uuid);
        builder.withClaim("role", role);
        builder.withExpiresAt(new Date(System.currentTimeMillis() + (1 * 3600 * 1000)));
        return builder.sign(algorithm);
    }


    public String generateToken(Long Id, Integer role) {
        JWTCreator.Builder builder = JWT.create();
        builder.withSubject(String.valueOf(Id));
        builder.withClaim("authority", role);
        builder.withExpiresAt(new Date(System.currentTimeMillis() + (1 * 3600 * 1000)));
        return builder.sign(algorithm);
    }

    public Map<String, Object> check(String token) {
        Map<String, Object> result = new HashMap<>();
        try {
            DecodedJWT decodedJWT = this.jwtVerifier.verify(token);
            String object = new String(Base64.decodeBase64(decodedJWT.getPayload()), "utf-8");
            result.put("code", 200);
            ObjectMapper objectMapper = new ObjectMapper();
            result.put("token", objectMapper.readValue(object, Token.class));
        } catch (Exception e) {
            result.put("code", 500);
        }
        return result;

    }
}
