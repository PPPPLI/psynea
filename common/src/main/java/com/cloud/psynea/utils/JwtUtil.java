package com.cloud.psynea.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.util.Date;
import java.util.Map;


public class JwtUtil {

    public final static RSAPublicKey PUBLIC_KEY;
    public final static RSAPrivateKey PRIVATE_KEY;
    public final static JWTVerifier VERIFIER;

    static {

        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(2046);
            KeyPair keyPair = generator.generateKeyPair();
            PUBLIC_KEY = (RSAPublicKey) keyPair.getPublic();
            PRIVATE_KEY = (RSAPrivateKey) keyPair.getPrivate();

            VERIFIER = JWT.require(Algorithm.RSA256(PUBLIC_KEY,PRIVATE_KEY)).build();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String createToken(String user, Instant expiredDate){

        Map<String, Object> header = Map.of("alg","RSA256","typ","JWT");

        return JWT.create()
                .withHeader(header)
                .withIssuer("psynea")
                .withExpiresAt(expiredDate)
                .withClaim("user",user)
                .sign(Algorithm.RSA256(PUBLIC_KEY,PRIVATE_KEY));
    }

    public static boolean verify(String token){

        try {
            VERIFIER.verify(token);
            return true;

        }catch (JWTVerificationException exception){

            return false;
        }
    }

    public static String extractToken(String token){

        try {

            DecodedJWT jwt = VERIFIER.verify(token);
            return jwt.getClaim("user").asString();
        }catch (JWTVerificationException e){

            return "";
        }
    }
}
