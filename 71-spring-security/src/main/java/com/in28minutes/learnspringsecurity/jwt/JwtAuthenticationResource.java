package com.in28minutes.learnspringsecurity.jwt;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;

//@RestController
public class JwtAuthenticationResource {
	
	private JwtEncoder jwtEncoder;
	
	public JwtAuthenticationResource(JwtEncoder jwtEncoder) {
		this.jwtEncoder = jwtEncoder;
	}
	
	// this will generate token 
	// authentication is the information you logged in with 
	@PostMapping("/authenticate") 
	public JwtRespose authenticate(Authentication authentication) {
		return new JwtRespose(createToken(authentication));
	}

	private String createToken(Authentication authentication) {
		var claims = JwtClaimsSet.builder()
								.issuer("self") 
								.issuedAt(Instant.now())
								.expiresAt(Instant.now().plusSeconds(60 * 30))
								.subject(authentication.getName()) // basically username 
								.claim("scope", createScope(authentication)) // roles like ROLE_USER 
								.build(); // create as Object 
		
		return jwtEncoder.encode(JwtEncoderParameters.from(claims))  // take the above claim, and
							.getTokenValue();    // extract the value, encode and return 
	}

	private String createScope(Authentication authentication) { 
		return authentication.getAuthorities().stream()   // get all the roles(authorities) from this user, 
								  // (it's found in authentication info) 
			.map(a -> a.getAuthority())
			.collect(Collectors.joining(" "));			
	}

}

record JwtRespose(String token) {}
