package com.purpleprint.network.purpleprintproject.jwt;

import com.purpleprint.network.purpleprintproject.auth.command.application.dto.TokenDTO;

import com.purpleprint.network.purpleprintproject.auth.command.domain.model.Child;
import com.purpleprint.network.purpleprintproject.auth.command.domain.model.User;
import com.purpleprint.network.purpleprintproject.common.customuser.ChildCustomUserDetailsService;
import com.purpleprint.network.purpleprintproject.common.customuser.CustomUserDetailsService;
import com.purpleprint.network.purpleprintproject.common.exception.TokenException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <pre>
 * Class : TokenProvider
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-03       전현정           최초 생성
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */
@Component
public class TokenProvider {

    private static final String AUTHORITIES_KEY = "auth";
    private static final String BEARER_TYPE = "bearer";
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 1440;            // 24시간

    private final CustomUserDetailsService userDetailsService;
    private final ChildCustomUserDetailsService childCustomUserDetailsService;

    private final Key key;


    public TokenProvider(@Value("${jwt.secret}") String secretKey, CustomUserDetailsService userDetailsService,
                         ChildCustomUserDetailsService childCustomUserDetailsService) {
        this.userDetailsService = userDetailsService;
        this.childCustomUserDetailsService = childCustomUserDetailsService;
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public TokenDTO generateTokenDto(User user) {

        // 권한들 가져오기
        List<String> roles =  Collections.singletonList(user.getRole().toString());

        Claims claims = Jwts
                .claims()
                .setSubject(user.getUsername());
        claims.put(AUTHORITIES_KEY, roles);

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("userId", user.getId());
        userInfo.put("userName", user.getUsername());

        claims.put("userInfo", userInfo);

        long now = (new Date()).getTime();

        // Access Token 생성
        Date accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        String accessToken = Jwts.builder()
                .setClaims(claims)
                //.claim(AUTHORITIES_KEY, roles)
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();



        return new TokenDTO(BEARER_TYPE, user.getName(), accessToken, accessTokenExpiresIn.getTime());
    }

    public TokenDTO childTokenDto(User user, Child child) {

        // 권한들 가져오기
        List<String> roles =  Collections.singletonList(user.getRole().toString());

        Claims claims = Jwts
                .claims()
                .setSubject(child.getName());

        claims.put(AUTHORITIES_KEY, roles);

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("userId", user.getId());
        userInfo.put("userName", user.getUsername());

        Map<String, Object> childInfo = new HashMap<>();
        childInfo.put("userChildId", child.getId());
        childInfo.put("userChildName", child.getName());

        claims.put("userInfo", userInfo);
        claims.put("childInfo", childInfo);

        long now = (new Date()).getTime();

        // Access Token 생성
        Date accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        String accessToken = Jwts.builder()
                .setClaims(claims)
                //.claim(AUTHORITIES_KEY, roles)
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        return new TokenDTO(BEARER_TYPE, user.getName(), accessToken, accessTokenExpiresIn.getTime());

    }

    public String getUserId(String accessToken) {
        return Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(accessToken)
                .getBody()
                .getSubject();
    }

    public Authentication getAuthentication(String accessToken) {
        // 토큰 복호화
        Claims claims = parseClaims(accessToken);

        if (claims.get(AUTHORITIES_KEY) == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        // 클레임에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());


        UserDetails userDetails = null;
        if(claims.get("childInfo") == null) {
            // UserDetails 객체를 만들어서 Authentication 리턴
            userDetails = userDetailsService.loadUserByUsername(this.getUserId(accessToken));

        } else {
            //childInfo가 있는 경우는 child name이 userId
            userDetails = childCustomUserDetailsService.loadUserByUsername(this.getUserId(accessToken));
        }

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            throw new TokenException("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            throw new TokenException("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            throw new TokenException("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            throw new TokenException("JWT 토큰이 잘못되었습니다.");
        }

    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
