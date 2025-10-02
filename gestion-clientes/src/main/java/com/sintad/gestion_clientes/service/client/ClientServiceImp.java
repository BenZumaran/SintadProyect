package com.sintad.gestion_clientes.service.client;

import com.sintad.gestion_clientes.model.ClientRequest;
import com.sintad.gestion_clientes.model.entity.Client;
import com.sintad.gestion_clientes.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

//To bring data to endpoints
@Service
public class ClientServiceImp implements ClientService {

    //brings connection with database
    @Autowired
    ClientRepository repository;

    //create new client
    @Override
    public Mono<Client> create(Client client) {
        client.setFechaRegistro(LocalDateTime.now());
        client.setEstado(true);
        return repository.save(client);
    }

    //to retrieve all clients according with limit and offset
    @Override
    public Flux<Client> getAll(long limit, long offset) {
        return repository.findAllPageable(limit,offset);
    }

    //to retrieve client by its id
    @Override
    public Mono<Client> getById(long id) {
        return repository.findById(id);
    }

    //to update a client
    @Override
    public Mono<Client> update(Client client, Mono<ClientRequest> clientRequest) {
        return clientRequest
                .map(request -> updateClientFromRequest(client, request))
                .flatMap(repository::save);
    }

    //delete a client (according asked)
    @Override
    public Mono<Void> deleteById(long id) {
        return repository.deleteById(id);
    }

    //to update just client state (for improve speed)
    @Override
    public Mono<Void> updateEstado(long id, boolean estado) {
        return repository.updateEstado(id,estado);
    }

    //to retrieve rows count
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
