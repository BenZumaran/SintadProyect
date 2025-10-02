package com.sintad.gestion_clientes.mapper;

import com.sintad.gestion_clientes.model.UserLoginRequest;
import com.sintad.gestion_clientes.model.entity.UserEntity;

//To manage data user conversions
public class UserMapper {

    private UserMapper(){}

    //From UserLoginRequest to UserEntity
    public static UserEntity toUser(UserLoginRequest request) {
        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        // id, role, pepper are not set from UserLoginRequest
        return user;
    }
}

