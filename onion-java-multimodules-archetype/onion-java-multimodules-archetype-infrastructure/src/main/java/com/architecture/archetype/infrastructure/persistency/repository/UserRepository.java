package com.architecture.archetype.infrastructure.persistency.repository;

import com.architecture.archetype.domain.model.UserModel;

/**
 * Interfaz que permite administrar operaciones en la base de datos sobre USER
 *
 * @version 1.0
 */
public interface UserRepository {

    UserModel saveUser(UserModel user);

}