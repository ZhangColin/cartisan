//package com.cartisan.common.utils;
//
//import io.jsonwebtoken.*;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//import java.util.Map;
//
///**
// * <p>Title: JwtUtil</p>
// * <p>Description: </p>
// *
// * @author colin
// */
//@ConfigurationProperties("cartisan.jwt")
//@Component
//public class JwtUtil {
//    private String key;
//    private long ttl;
//
//    public String getKey() {
//        return key;
//    }
//
//    public void setKey(String key) {
//        this.key = key;
//    }
//
//    public long getTtl() {
//        return ttl;
//    }
//
//    public void setTtl(long ttl) {
//        this.ttl = ttl;
//    }
//
//    public String createJwt(String id, Map<String, Object> infos) {
//        long nowMillis = System.currentTimeMillis();
//        Date now = new Date(nowMillis);
//
//        JwtBuilder builder = Jwts.builder()
//                .setId(id)
//                .setIssuedAt(now)
//                .signWith(SignatureAlgorithm.HS256, key);
//
//        infos.forEach(builder::claim);
//
//        if (ttl > 0) {
//            builder.setExpiration(new Date(nowMillis + ttl));
//        }
//
//        return builder.compact();
//    }
//
//    public Claims parseJWt(String token) {
//        return Jwts.parser()
//                .setSigningKey(key)
//                .parseClaimsJws(token)
//                .getBody();
//    }
//}
