package com.architecture.archetype.infrastructure.persistency.repository;

import org.springframework.stereotype.Repository;

import com.architecture.archetype.api.dto.User;
import com.architecture.archetype.domain.driver.repository.UserRepository;
import com.architecture.archetype.infrastructure.entity.UserEntity;
import com.architecture.archetype.infrastructure.persistency.dao.UserDao;
import com.architecture.archetype.infrastructure.persistency.factory.FactoryUserEntity;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserDao userDao;
    private final FactoryUserEntity factoryUserEntity;

    @Override
    public User saveUser(User user) {
        UserEntity userEntity = this.userDao.save(this.factoryUserEntity.userToUserEntity(user));
        return this.factoryUserEntity.userEntityToUser(userEntity);
    }
}
