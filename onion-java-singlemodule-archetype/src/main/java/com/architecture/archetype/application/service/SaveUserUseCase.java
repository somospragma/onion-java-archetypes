package com.architecture.archetype.application.service;

import com.architecture.archetype.api.dto.User;
import com.architecture.archetype.domain.driver.gateway.RestrictiveListGateway;
import com.architecture.archetype.domain.driver.repository.UserRepository;
import com.architecture.archetype.domain.error.FoundOnRestrictiveListException;

import lombok.RequiredArgsConstructor;

//TODO: son como servicios en mvc, aunque la logica de negocio va dentro del modelo
@RequiredArgsConstructor
/**
 * Logica de negocio
 */
public class SaveUserUseCase {
    private final UserRepository userRepository;
    private final RestrictiveListGateway restrictiveListAdapter;

    public User saveUser(User user) {
        if (this.restrictiveListAdapter.getValidate(user.getName())) {
            return userRepository.saveUser(user);
        } else {
            throw new FoundOnRestrictiveListException(user.getName().concat(" se encuentra en lista restrictiva."));
        }
    }
}
