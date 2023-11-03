package az.ingress.bookstore.security;

import az.ingress.bookstore.constant.SecurityConstant;
import az.ingress.bookstore.model.jwt.JwtToken;
import az.ingress.bookstore.util.DateHelper;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class JWTProvider {

    private final JWTVerifier jwtVerifier;
    private final SecurityConstant securityConstants;

    public JWTProvider(SecurityConstant securityConstants) {
        this.securityConstants = securityConstants;
        this.jwtVerifier = JWT.require(Algorithm.HMAC256(securityConstants.getSecretKey()))
                .withSubject("Book Store")
                .withIssuer("Book Store Admin")
                .build();
    }

    public JwtToken getJWTToken(String accountId) {
        Date createDate = DateHelper.now();
        Date expirationDate = getExpirationDate();
        String jwtToken = JWT
                .create()
                .withSubject("Book Store")
                .withExpiresAt(expirationDate)
                .withIssuedAt(createDate)
                .withClaim("accountId", accountId)
                .withIssuer("Book Store Admin")
                .sign(Algorithm.HMAC256(securityConstants.getSecretKey()));

        return JwtToken.builder()
                .token(jwtToken)
                .createDate(createDate.getTime())
                .expirationDate(expirationDate.getTime())
                .build();
    }

    public String extractUserId(String token) {
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        return decodedJWT.getClaim("accountId").asString();
    }

    private Date getExpirationDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 3);
        return calendar.getTime();
    }
}