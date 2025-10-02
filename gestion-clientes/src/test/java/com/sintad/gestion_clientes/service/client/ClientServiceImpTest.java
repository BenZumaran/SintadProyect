package com.sintad.gestion_clientes.service.client;

import com.sintad.gestion_clientes.model.entity.Client;
import com.sintad.gestion_clientes.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceImpTest {

    @Mock
    ClientRepository repository;

    @InjectMocks
    ClientServiceImp clientService;

    Client client;

    @BeforeEach
    void setUp(){
        client = Client.builder()
                .id(Long.decode("1"))
                .nombre("Ben")
                .apellido("Zuma")
                .correo("Ben.correo@correo.com")
                .telefono("987876765")
                .estado(true)
                .fechaRegistro(LocalDateTime.now())
                .build();
    }


    @Test
    void create() {
    }

    @Test
    void getAll() {
        when(repository.findAllPageable(any(Long.class),any(Long.class))).thenReturn(Flux.just(client));
        StepVerifier.create(clientService.getAll(5,0))
                .expectSubscription()
                .expectNextCount(1)
                .verifyComplete();
        verify(repository,times(1)).findAllPageable(any(Long.class),any(Long.class));
    }

    @Test
    void getById() {
        when(repository.findById(any(Long.class))).thenReturn(Mono.just(client));
        StepVerifier.create(clientService.getById(1))
                .expectSubscription()
                .expectNextCount(1)
                .verifyComplete();
        verify(repository,times(1)).findById(any(Long.class));
    }

    @Test
    void update() {
        when(repository.save(any(Client.class))).thenReturn(Mono.just(client));
        StepVerifier.create(clientService.create(client))
                .expectSubscription()
                .expectNextCount(1)
                .verifyComplete();
        verify(repository,times(1)).save(any(Client.class));
    }

    @Test
    void deleteById() {
        when(repository.deleteById(any(Long.class))).thenReturn(Mono.empty());
        StepVerifier.create(clientService.deleteById(Long.decode("1")))
                .expectSubscription()
                .verifyComplete();
        verify(repository,times(1)).deleteById(any(Long.class));
    }

    @Test
    void updateEstado() {
        when(repository.updateEstado(any(Long.class),any(Boolean.class))).thenReturn(Mono.empty());
        StepVerifier.create(clientService.updateEstado(Long.decode("4"),false))
                .expectSubscription()
                .verifyComplete();
        verify(repository,times(1)).updateEstado(any(Long.class),any(Boolean.class));
    }

    @Test
    void getRowsCount() {
        when(repository.getCount()).thenReturn(Mono.just(Long.decode("55")));
        StepVerifier.create(clientService.getRowsCount())
                .expectSubscription()
                .expectNextCount(1)
                .verifyComplete();
        verify(repository,times(1)).getCount();
    }
}