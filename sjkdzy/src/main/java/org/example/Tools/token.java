package org.example.Tools;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class token {
    private static final int EXPIRE_DATE = 18000;
    private static final String TOKEN_SECRET = "jzh";

    public static String token(String user, String id, String password) {
        String token = "";
        try {
            //过期时间
            Date date = new Date(System.currentTimeMillis() + (EXPIRE_DATE * 1000));
            //秘钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //设置头部信息
            Map<String, Object> header = new HashMap<>();
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            //携带username，password信息，生成签名
            token = JWT.create()
                    .withHeader(header)
                    .withClaim("user", user)
                    .withClaim("id", id)
                    .withClaim("password", password).withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return token;
    }

    public static DecodedJWT verify(String token) {
        /**
         * @desc 验证token，通过返回true
         * @params [token]需要校验的串
         **/
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
//            System.out.println(jwt.getClaim("username").asString());
//            System.out.println(jwt.getExpiresAt().getTime());
//            System.out.println(System.currentTimeMillis());
            return jwt;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
