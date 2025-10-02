package com.sintad.gestion_clientes.service.security;

import com.sintad.gestion_clientes.model.entity.PrincipalUser;
import com.sintad.gestion_clientes.model.entity.UserEntity;
import com.sintad.gestion_clientes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//implements user that connects with database and reactive user details to security purposes
@Service
public class PrincipalUserDetailService implements ReactiveUserDetailsService {
	
	@Autowired
    UserRepository repository;
	
	@Autowired
	JwtService jwtService;

    @Autowired
    PasswordEncoder encoder;

	@Override
    public Mono<UserDetails> findByUsername(String username) {
        return this.findUserByUsername(username)
                .switchIfEmpty(Mono.error(new UsernameNotFoundException("User not found")))
                .map(PrincipalUser::new);
	}
	
	public Mono<UserEntity> add(UserEntity user) {
//		user.setPepper(BCrypt.gensalt(15));
//		user.setPassword(encoder.encode(user.getPepper() + user.getPassword()));
		user.setPassword(encoder.encode(user.getPassword()));
		return repository.save(user);
	}
	
	public Flux<UserEntity> findAll() {
		return repository.findAll();
	}
	
	public Mono<UserEntity> findUserByUsername(String username) {
		return repository.findByUsername(username);
	}

	public Mono<String> verify(UserEntity user) {
        return findUserByUsername(user.getUsername())
                .switchIfEmpty(Mono.error(new UsernameNotFoundException("Username not found")))
                .map(validationUser->
                    validationUser.getPassword().equals(user.getPassword()) ?
                            jwtService.generateToken(user.getUsername())
                            : "Password incorrect"
                );
	}

}
