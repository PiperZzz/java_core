package base.core.spring_security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class MyJwt {
    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    @Value("${security.jwt.token.expire-length}")
    private long validityInMilliseconds;

    public String generateToken(Authentication authentication) {// Authentication类是Spring Security中用于处理身份验证和授权的关键类
        String username = authentication.getName();// 提取用户名
        Collection<? extends GrantedAuthority> roles = authentication.getAuthorities(); // 提取用户角色集合

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds); // 设置token的过期时间
        Claims claims = Jwts.claims().setSubject(username); // Jwts的静态方法claims()设置token的主体部分，即用户名，返回的是一个Claims对象
        claims.put("roles", roles.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())); // 设置token的负载部分的roles属性

        return Jwts.builder() // Jwts的静态方法builder()创建一个JwtBuilder对象
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey) // 设置token的签名算法和密钥
                .compact(); // 生成token
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtException("JWT token is expired or invalid");
        }
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        String[] roles = claims.get("roles", String.class).split(",");
        return new UsernamePasswordAuthenticationToken(claims.getSubject(), "",
                Arrays.stream(roles).map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
    }
}
