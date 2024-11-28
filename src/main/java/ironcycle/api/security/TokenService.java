package ironcycle.api.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import ironcycle.api.model.admin.Admin;

@Service
public class TokenService {
	
	@Value("{api.security.token.secret}")
	private String secret;
	
	public String generateToken(Admin admin) {
		try {
			var algorithm = Algorithm.HMAC256(secret);
			return JWT.create()
					.withIssuer("API Ironcycle")
					.withSubject(admin.getUsername())
					.withExpiresAt(dateExpiration())
					.sign(algorithm);
		} catch (JWTCreationException exception) {
			throw new RuntimeException("Erro ao gerar token JWT", exception);
		}
	}
	
	public String getSubject(String tokenJWT) {
		try {
			var algorithm = Algorithm.HMAC256(secret);
			return JWT.require(algorithm)
					.withIssuer("API games")
					.build()
					.verify(tokenJWT)
					.getSubject();
		} catch (JWTVerificationException exception) {
		    System.out.println("TOKEN: " +tokenJWT);
		    exception.printStackTrace();
		    throw new RuntimeException("Token JWT inválido ou expirado");
		}
	}
	
	private Instant dateExpiration() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}

}
