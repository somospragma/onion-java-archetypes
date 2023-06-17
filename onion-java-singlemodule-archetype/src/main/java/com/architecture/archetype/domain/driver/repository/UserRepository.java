package com.architecture.archetype.domain.driver.repository;

import com.architecture.archetype.api.dto.User;

/**
 * Interfaz que permite administrar operaciones en la base de datos sobre USER
 *
 * @version 1.0
 */
public interface UserRepository {
    User saveUser(User user);
}
