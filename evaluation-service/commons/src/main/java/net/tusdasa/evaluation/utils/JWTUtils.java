package net.tusdasa.evaluation.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class JWTUtils {
    private final String SECRET_KEY = "9O2B+amfn52nzowCysmC7+hfuA0q9xvxcgLU1UDVYwM=";

    private final String MAC_NAME = "HMacSHA256";

    private KeyGenerator keyGenerator;

    private SecretKey secretKey;

    private Algorithm algorithm;

    private Verification verification;

    private JWTVerifier jwtVerifier;


    public JWTUtils() {
    }

    public JWTUtils(Boolean enable, String secret) {
        try {

            if (enable) {
                if (secret != null) {
                    this.algorithm = Algorithm.HMAC256(secret.getBytes());
                } else {
                    this.algorithm = Algorithm.HMAC256(SECRET_KEY.getBytes());
                }
            } else {
                this.keyGenerator = KeyGenerator.getInstance(MAC_NAME);
                this.secretKey = keyGenerator.generateKey();
                this.algorithm = Algorithm.HMAC256(secretKey.getEncoded());
            }

            this.verification = JWT.require(algorithm);
            this.jwtVerifier = verification.build();

        } catch (NoSuchAlgorithmException e) {

        }

    }

    public String generateToken(Long Id, Integer role) {
        JWTCreator.Builder builder = JWT.create();
        builder.withSubject(String.valueOf(Id));
        builder.withIssuer("authority");
        builder.withClaim("role", role);
        builder.withExpiresAt(new Date(System.currentTimeMillis() + (24 * 3600 * 1000)));
        return builder.sign(algorithm);
    }

    public String check(String token) throws JWTVerificationException {
        DecodedJWT decodedJWT = this.jwtVerifier.verify(token);
        try {
            return new String(Base64.decodeBase64(decodedJWT.getPayload()), "utf-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}
