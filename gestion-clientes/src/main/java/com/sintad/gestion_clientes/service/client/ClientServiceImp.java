package com.sintad.gestion_clientes.service.client;

import com.sintad.gestion_clientes.model.ClientRequest;
import com.sintad.gestion_clientes.model.entity.Client;
import com.sintad.gestion_clientes.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class ClientServiceImp implements ClientService {

    @Autowired
    ClientRepository repository;

    @Override
    public Mono<Client> create(Client client) {
        client.setFechaRegistro(LocalDateTime.now());
        client.setEstado(true);
        return repository.save(client);
    }

    @Override
    public Flux<Client> getAll(long limit, long offset) {
        return repository.findAllPageable(limit,offset);
    }

    @Override
    public Mono<Client> getById(long id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Client> update(Client client, Mono<ClientRequest> clientRequest) {
        return clientRequest
                .map(request -> updateClientFromRequest(client, request))
                .flatMap(repository::save);
    }

    @Override
    public Mono<Void> deleteById(long id) {
        return repository.deleteById(id);
    }

    @Override
    public Mono<Void> updateEstado(long id, boolean estado) {
        return repository.updateEstado(id,estado);
    }

    @Override
    public Mono<Long> getRowsCount() {
        return repository.getCount();
    }


    private Client updateClientFromRequest(Client client, ClientRequest request) {
        if (client == null || request == null) return client;
        if (request.getNombre() != null) {
            client.setNombre(request.getNombre());
        }
        if (request.getApellido() != null) {
            client.setApellido(request.getApellido());
        }
        if (request.getCorreo() != null) {
            client.setCorreo(request.getCorreo());
        }
        if (request.getTelefono() != null) {
            client.setTelefono(request.getTelefono());
        }
        return client;
    }


}
