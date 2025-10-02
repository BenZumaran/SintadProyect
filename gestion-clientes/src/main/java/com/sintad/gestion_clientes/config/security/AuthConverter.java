package com.sintad.gestion_clientes.config.security;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

//Convert Header Values into Authentication token (bearer token)
@Component
public class AuthConverter implements ServerAuthenticationConverter {


    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        return Mono.justOrEmpty(
                exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION)
        )
                .filter(res -> res.startsWith("Bearer "))
                .map(res -> res.substring(7))
                .map(BearerToken::new);
    }
}
