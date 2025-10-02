package com.sintad.gestion_clientes.repository;


import com.sintad.gestion_clientes.model.entity.UserEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<UserEntity,Long> {

    //To manage security
    @Query("select * from users where username = :username")
    Mono<UserEntity> findByUsername(String username);
}
