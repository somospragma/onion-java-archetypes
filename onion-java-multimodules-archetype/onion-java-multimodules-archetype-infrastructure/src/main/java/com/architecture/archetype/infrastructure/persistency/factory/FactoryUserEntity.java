package com.architecture.archetype.infrastructure.persistency.factory;

import com.architecture.archetype.domain.model.UserModel;
import com.architecture.archetype.infrastructure.entity.UserEntity;

/*
mapeo domain --> jpa
 */
public class FactoryUserEntity {

    public UserEntity userToUserEntity(UserModel user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setCode(user.getCode());
        userEntity.setName(user.getName());
        userEntity.setPassword(user.getPassword());
        return userEntity;
    }

    public UserModel userEntityToUser(UserEntity userEntity) {
        return new UserModel(userEntity.getName(), userEntity.getCode(), userEntity.getPassword());
    }
}
