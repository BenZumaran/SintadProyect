package com.sintad.gestion_clientes.config.security;

import com.sintad.gestion_clientes.service.security.JwtService;
import com.sintad.gestion_clientes.service.security.PrincipalUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class AuthManager implements ReactiveAuthenticationManager {

    @Autowired
    JwtService jwtService;
    @Autowired
    PrincipalUserDetailService userService;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.justOrEmpty(authentication)
                .cast(BearerToken.class)
                .flatMap(auth->{
                    String username =jwtService.extractUsername(auth.getCredentials());
                    Mono<UserDetails> foundUser = userService.findByUsername(username);

                    return  foundUser.map(userDetails->{
                            if (jwtService.validateToken(auth.getCredentials(),userDetails) )
                                return new UsernamePasswordAuthenticationToken(
                                    userDetails.getUsername(),userDetails.getPassword(),userDetails.getAuthorities());
                            throw new IllegalArgumentException("Invalid Token");
                    });


                });
    }
}
