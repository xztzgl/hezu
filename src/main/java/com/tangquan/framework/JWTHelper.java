package com.tangquan.framework;

import com.tangquan.system.enums.GatewayError;
import com.tangquan.system.exception.ApiException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Component
public class JWTHelper {

    public static final String AUTH_TYPE = "Bearer ";
    @Value("${tq.framework.jwt.ttl-seconds}")
    private long jwtTtlSeconds;
    @Value("${tq.framework.jwt.secret}")
    private String jwtSecret;
    @Value("${tq.framework.jwt.refresh-window-seconds}")
    private long jwtRefreshWindowSeconds;
    //The JWT signature algorithm we will be using to sign the token
    private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    private Key signingKey;

    @PostConstruct
    public void init() {
        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtSecret);
        signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
    }

    public String createJWT(String id, String issuer, String subject) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //Let's set the JWT Claims
        JwtBuilder builder =
                Jwts.builder().setId(id).setIssuer(issuer).setSubject(subject).setIssuedAt(now)
                        .signWith(signatureAlgorithm, signingKey);

        long expMillis = nowMillis + jwtTtlSeconds * 1000;
        Date exp = new Date(expMillis);
        builder.setExpiration(exp);
        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    public Jws<Claims> parseJWT(String jwt) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(jwt);
        return claimsJws;
    }

    public boolean needRefresh(Jws<Claims> jws) {
        return jws.getBody().getIssuedAt().getTime() + jwtRefreshWindowSeconds * 1000 < System.currentTimeMillis();
    }
}
