package com.sanskar.job.security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;

public class JwtProvider {

  private final SecretKey secretKey= Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

  public String generateToken(Authentication authentication,String userId) {
      Collection<? extends GrantedAuthority> authorities=authentication.getAuthorities();
      String roles=populateAuthorities(authorities);

      return Jwts.builder().issuedAt(new Date())
              .expiration(new Date(System.currentTimeMillis()+864000000))
              .claim("email",authentication.getName())
              .claim("authorities",roles)
              .claim("userId",userId)
              .signWith(secretKey).compact();
  }

    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
      return "";
    }
}
