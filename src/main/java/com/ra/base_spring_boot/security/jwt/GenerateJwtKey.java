// package com.ra.base_spring_boot.security.jwt;

// import io.jsonwebtoken.io.Encoders;
// import io.jsonwebtoken.security.Keys;
// import io.jsonwebtoken.SignatureAlgorithm;

// public class GenerateJwtKey {
//     public static void main(String[] args) {
//         var key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//         System.out.println("🔑 JWT Secret Key (Base64): " + Encoders.BASE64.encode(key.getEncoded()));
//     }
// }
