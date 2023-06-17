package com.architecture.archetype.infrastructure.persistency.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.architecture.archetype.infrastructure.entity.UserEntity;

/**
 * Interface que utiliza los métodos propios de jpa o los que se requieran personalizados
 */
public interface UserDao extends JpaRepository<UserEntity, Long> {
}
