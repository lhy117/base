package com.hl.shiro.jwt;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * jwt的生成器
 * @author lhy
 * @date 2018-06-05
 */
public class JWTGenerator {
	// Sample method to construct a JWT
	/**
	 * 创建jwt的key
	 * @param id key的id，可以取用户id
	 * @param issuer 发行者
	 * @param subject 用户的主题，可以取用户名
	 * @param ttlMillis 过期时间
	 * @param secret 加密字符串
	 * @return
	 */
	public static String createJWT(String id, String issuer, String subject, long ttlMillis, String secret) {
		// The JWT signature algorithm we will be using to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		// We will sign our JWT with our ApiKey secret
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
		// Let's set the JWT Claims
		JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now).setSubject(subject).setIssuer(issuer)
				.signWith(signatureAlgorithm, signingKey);
		// if it has been specified, let's add the expiration
		if (ttlMillis >= 0) {
			long expMillis = nowMillis + ttlMillis;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}
		// Builds the JWT and serializes it to a compact, URL-safe string
		return builder.compact();
	}
	// Sample method to validate and read the JWT
	/**
	 * 解析jwt
	 * @param jwt
	 * @param secret
	 */
	public static Claims parseJWT(String jwt, String secret) {
		// This line will throw an exception if it is not a signed JWS (as expected)
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secret)).parseClaimsJws(jwt)
				.getBody();
		return claims;
	}
	/**
	 * 解析token状态
	 * @param jwt
	 * @param secret
	 * @return
	 */
	public static TokenState getState(String jwt, String secret) {
		try {
			Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secret)).parseClaimsJws(jwt).getBody();
			return TokenState.VALID;
		} catch (ExpiredJwtException e) {
			// token过期
			return TokenState.EXPIRED;
		} catch (Exception e) {
			// token格式不合法导致的异常
			return TokenState.INVALID;
		}
	}
}
