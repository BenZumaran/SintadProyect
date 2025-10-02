package com.sintad.gestion_clientes.mapper;

import com.sintad.gestion_clientes.model.entity.Client;
import com.sintad.gestion_clientes.model.ClientRequest;
import com.sintad.gestion_clientes.model.ClientResponse;
import org.springframework.cglib.core.Local;

import java.time.ZoneId;

//To manage client data conversions
public class ClientMapper {

    private ClientMapper(){}

    //From Client to Client Response
    public static ClientResponse toClientResponse(Client client) {
        ClientResponse response = new ClientResponse();
        response.setId(client.getId().intValue());
        response.setNombre(client.getNombre());
        response.setApellido(client.getApellido());
        response.setCorreo(client.getCorreo());
        response.setTelefono(client.getTelefono());
        response.setEstado(client.getEstado());
        response.setFechaRegistro(client.getFechaRegistro() != null ? client.getFechaRegistro().atZone(ZoneId.systemDefault()).toOffsetDateTime() : null);
        return response;
    }
    //From ClientRequest to Client Client
    public static Client toClient(ClientRequest request) {
        return Client.builder()
            .nombre(request.getNombre())
            .apellido(request.getApellido())
            .correo(request.getCorreo())
            .telefono(request.getTelefono())
            .build();
    }

}
