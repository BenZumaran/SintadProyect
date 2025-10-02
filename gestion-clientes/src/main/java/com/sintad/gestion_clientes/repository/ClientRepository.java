package com.sintad.gestion_clientes.repository;


import com.sintad.gestion_clientes.model.entity.Client;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientRepository extends ReactiveCrudRepository<Client,Long> {

    //To just update the state
    @Modifying
    @Query("update clientes set estado = :estado where id = :id")
    Mono<Void> updateEstado(long id, boolean estado);

    //To return pageable query
    @Query("select * from clientes order by clientes.id limit :limit offset :offset")
    Flux<Client> findAllPageable(long limit, long offset);

    //to count all rows
    @Query("select count(clientes.id) from clientes")
    Mono<Long> getCount();

}
