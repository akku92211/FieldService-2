package com.KeyStone.FieldService2.Security;

import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.KeyStone.FieldService2.Entity.UserEntity;
import com.KeyStone.FieldService2.Enum.Permissions;
import com.KeyStone.FieldService2.Repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JVMUtil {

    private final SecretKey key;

    private final long validTokenTime = 12 * 60 * 60 * 1000L;

    public JVMUtil() {

        String secret = System.getenv("JVM_SECRET");

        if (secret == null || secret.isEmpty()) {
            secret = "Replace this place with secret code";
        }

        key = Keys.hmacShaKeyFor(
                secret.getBytes(StandardCharsets.UTF_8)
        );
    }

    public String generateToken(UserRepository user) {

        Map<String, Object> claims = new HashMap<>();

        claims.put("role", user.getRole().name());

        Set<Permissions> perm =RoleBasedPermissions.getRoleBasedPermission()
                        .get(user.getRole());

        claims.put("permissions", perm);

        Date now = new Date();

        Date expire = new Date(
                now.getTime() + validTokenTime
        );

        return Jwts.builder()
                .claims(claims)
                .subject(user.getUserEmail())
                .issuedAt(now)
                .expiration(expire)
                .signWith(key)
                .compact();
    }

    public boolean validateToken(String token) {

        try {

            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);

            return true;

        } catch (Exception e) {

            return false;
        }
    }

    public Claims getClaim(String token) {

        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getUserEmail(String token) {

        return getClaim(token).getSubject();
    }
    public String extractToken(String header) {
   	 if(header !=null && header.startsWith("Bearer ")) {
   		 return header.substring(7);
   	 }
   	 return null;
    }
}