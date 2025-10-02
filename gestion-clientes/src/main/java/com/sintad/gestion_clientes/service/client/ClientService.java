package com.sintad.gestion_clientes.service.client;

import com.sintad.gestion_clientes.model.ClientRequest;
import com.sintad.gestion_clientes.model.entity.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {
    Mono<Client> create(Client client);
    Flux<Client> getAll(long limit, long offset);
    Mono<Client> getById(long id);
    Mono<Client> update(Client client, Mono<ClientRequest> clientRequest);
    Mono<Void> deleteById(long id);
    Mono<Void> updateEstado(long id, boolean estado);
    Mono<Long> getRowsCount();
}
