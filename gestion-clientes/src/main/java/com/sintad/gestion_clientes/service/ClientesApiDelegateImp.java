package com.sintad.gestion_clientes.service;

import com.sintad.gestion_clientes.api.ClientesApiDelegate;
import com.sintad.gestion_clientes.mapper.ClientMapper;
import com.sintad.gestion_clientes.mapper.UserMapper;
import com.sintad.gestion_clientes.model.ClientRequest;
import com.sintad.gestion_clientes.model.ClientResponse;
import com.sintad.gestion_clientes.model.UserLoginRequest;
import com.sintad.gestion_clientes.service.client.ClientService;
import com.sintad.gestion_clientes.service.security.PrincipalUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


//To implements endpoints comming from OpenApi
@Service
public class ClientesApiDelegateImp implements ClientesApiDelegate {

    //brings data
    @Autowired
    ClientService clientService;

    //To manage user Authentication and token generation
    @Autowired
    PrincipalUserDetailService principalUserDetailService;

    @Override
    public Mono<ClientResponse> createClient(Mono<ClientRequest> clientRequest, ServerWebExchange exchange) {
        return clientRequest.map(ClientMapper::toClient)
                .flatMap(clientService::create)
                .map(ClientMapper::toClientResponse);
    }

    @Override
    public Mono<Void> deleteClient(Integer id, ServerWebExchange exchange) {
        return clientService.deleteById(id.longValue());
    }

    @Override
    public Flux<ClientResponse> getAllClients(Integer limit, Integer offset, ServerWebExchange exchange) {
        long limit_validated = limit == null ? 10 : limit.longValue();
        long offset_validated = offset == null ? 0 : offset.longValue();
        return clientService.getAll(limit_validated,offset_validated).map(ClientMapper::toClientResponse);
    }

    @Override
    public Mono<ClientResponse> getClientById(Integer id, ServerWebExchange exchange) {
        return clientService.getById(id.longValue())
                .map(ClientMapper::toClientResponse);
    }

    @Override
    public Mono<ClientResponse> updateClient(Integer id, Mono<ClientRequest> clientRequest, ServerWebExchange exchange) {
        return clientService.getById(id.longValue())
                .flatMap(client -> clientService.update(client, clientRequest))
                .map(ClientMapper::toClientResponse);

    }

    @Override
    public Mono<Void> updateClientEstado(Integer id, Boolean state, ServerWebExchange exchange){
        return clientService.updateEstado(id,state);
    }

    @Override
    public Mono<Integer> getClientsCount(ServerWebExchange exchange){
        return clientService.getRowsCount().map(Long::intValue);
    }

    @Override
    public Mono<String> userLogin(Mono<UserLoginRequest> userLoginRequest, ServerWebExchange exchange){
        return userLoginRequest.map(UserMapper::toUser)
                .flatMap(principalUserDetailService::verify);
    }

}
