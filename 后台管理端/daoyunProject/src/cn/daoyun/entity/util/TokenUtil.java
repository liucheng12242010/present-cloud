package cn.daoyun.entity.util;

import java.security.Key;
import java.sql.Date;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;

import cn.daoyun.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenUtil {
	private static final String secret = "22640E66C97339C027F77759025CAD17";
	public static String createJWT(User user,long TTLMillis)
	{
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		Key signingKey = generalKey(secret);
		JwtBuilder builder = Jwts.builder()
				.setHeaderParam("typ", "JWT")
				.claim("userid", user.getUserId())
				.claim("username",user.getUserName())
				.setIssuedAt(now)
				.setSubject(user.getUserName())
				.signWith(signatureAlgorithm, signingKey);
		if(TTLMillis>=0)
		{
			long expMillis = nowMillis + TTLMillis;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}
		return builder.compact();
	}
	public String validateJWT(String token)
	{ 
		Claims claims;
		try
		{
			Key signingKey = generalKey(secret);
			claims = Jwts.parser()
							.setSigningKey(signingKey)
							.parseClaimsJws(token)
							.getBody();
			return (String)claims.get("userid");
		}catch(Exception e)
		{
			return null;
			
		}
	}
	public static SecretKey generalKey(String secret){		
		byte[] encodedKey = Base64.decodeBase64(secret);	    
		SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");	    
		return key;	
	}
}
