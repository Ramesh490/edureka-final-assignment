package com.edureka.config;

import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.server.SecurityWebFilterChain;

import reactor.core.publisher.Mono;

@Configuration
public class SecurityConfig {

    private static final String SECRET =
            "X9kP2vLm8QwRtYz12345JwtSecretKeySecure2026HSPXXxxghtf";

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        http
            .csrf(ServerHttpSecurity.CsrfSpec::disable)
            .authorizeExchange(exchanges -> exchanges
                .pathMatchers("/auth/**").permitAll()
                .pathMatchers(HttpMethod.POST, "/api/products/newProduct")
                .hasAuthority("ROLE_PRODUCT_OWNER")
                .anyExchange().permitAll()
            )
            .oauth2ResourceServer(oauth2 ->
                oauth2.jwt(jwt ->
                    jwt.jwtAuthenticationConverter(this::convert)
                )
            );

        return http.build();
    }

    private Mono<JwtAuthenticationToken> convert(Jwt jwt) {

        String role = jwt.getClaimAsString("roles");

        return Mono.just(
            new JwtAuthenticationToken(
                jwt,
                List.of(new SimpleGrantedAuthority(role))
            )
        );
    }

    @Bean
    public ReactiveJwtDecoder jwtDecoder() {

        SecretKeySpec secretKey =
                new SecretKeySpec(
                        SECRET.getBytes(StandardCharsets.UTF_8),
                        "HmacSHA256"
                );

        return NimbusReactiveJwtDecoder
                .withSecretKey(secretKey)
                .build();
    }
}
