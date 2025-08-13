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
import java.util.Map;


public class JwtUtil {

    private static final String SIGNATURE;
    private final static JWTVerifier VERIFIER;

    static {

        SIGNATURE = "bWljcm9zZXJ2aWNlcw";
        VERIFIER = JWT.require(Algorithm.HMAC256(SIGNATURE)).build();
    }

    public static String createToken(String user, Instant expiredDate){

        Map<String, Object> header = Map.of("alg","HMAC256","typ","JWT");

        return JWT.create()
                .withHeader(header)
                .withIssuer("psynea")
                .withExpiresAt(expiredDate)
                .withClaim("user",user)
                .sign(Algorithm.HMAC256(SIGNATURE));
    }

    public static String createInternalTokenForServer(){

        Map<String, Object> header = Map.of("alg","HMAC256","typ","JWT");

        return JWT.create()
                .withHeader(header)
                .withIssuer("psynea")
                .sign(Algorithm.HMAC256(SIGNATURE));
    }

    public static boolean verify(String token){

        try {
            DecodedJWT decodedJWT = VERIFIER.verify(token);
            System.out.println(decodedJWT.getExpiresAt());
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
