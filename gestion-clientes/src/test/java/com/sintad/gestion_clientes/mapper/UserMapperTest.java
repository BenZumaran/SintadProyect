package com.sintad.gestion_clientes.mapper;

import com.sintad.gestion_clientes.model.UserLoginRequest;
import com.sintad.gestion_clientes.model.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    @Test
    void toUser() {
        UserLoginRequest user = new UserLoginRequest()
                .username("username")
                .password("password");
        var response = UserMapper.toUser(user);
        Assertions.assertEquals(UserEntity.class, response.getClass());
        Assertions.assertNotNull(response);

    }
}