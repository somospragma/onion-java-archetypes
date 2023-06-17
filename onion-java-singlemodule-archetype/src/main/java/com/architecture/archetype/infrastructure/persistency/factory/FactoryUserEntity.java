package com.architecture.archetype.infrastructure.persistency.factory;

import com.architecture.archetype.api.dto.User;
import com.architecture.archetype.infrastructure.entity.UserEntity;

/*
mapeo domain --> jpa
 */
public class FactoryUserEntity {

    public UserEntity userToUserEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setCode(user.getCode());
        userEntity.setName(user.getName());
        userEntity.setPassword(user.getPassword());
        return userEntity;
    }

    public User userEntityToUser(UserEntity userEntity) {
        return new User(userEntity.getName(), userEntity.getCode(), userEntity.getPassword());
    }
}
