package com.sintad.gestion_clientes.mapper;

import com.sintad.gestion_clientes.model.UserLoginRequest;
import com.sintad.gestion_clientes.model.entity.UserEntity;

public class UserMapper {

    private UserMapper(){}

    public static UserEntity toUser(UserLoginRequest request) {
        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        // id, role, pepper are not set from UserLoginRequest
        return user;
    }
}

