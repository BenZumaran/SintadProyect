package com.sintad.gestion_clientes.mapper;

import com.sintad.gestion_clientes.model.ClientRequest;
import com.sintad.gestion_clientes.model.ClientResponse;
import com.sintad.gestion_clientes.model.entity.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ClientMapperTest {

    @Test
    void toClientResponse() {
        Client client = Client.builder()
                .id(Long.decode("1"))
                .nombre("Ben")
                .apellido("Zuma")
                .correo("Ben.correo@correo.com")
                .telefono("987876765")
                .estado(true)
                .fechaRegistro(LocalDateTime.now())
                .build();
        var response =ClientMapper.toClientResponse(client);
        Assertions.assertEquals(ClientResponse.class,response.getClass());
        Assertions.assertNotNull(response);
        client.setFechaRegistro(null);
        response =ClientMapper.toClientResponse(client);
        Assertions.assertNull(response.getFechaRegistro());

    }

    @Test
    void toClient() {
        ClientRequest clientRequest = new ClientRequest()
                .nombre("Ben")
                .apellido("Zuma")
                .correo("Ben.correo@correo.com")
                .telefono("987876765");
        var response =ClientMapper.toClient(clientRequest);
        Assertions.assertEquals(Client.class,response.getClass());
        Assertions.assertNotNull(response);


    }
}