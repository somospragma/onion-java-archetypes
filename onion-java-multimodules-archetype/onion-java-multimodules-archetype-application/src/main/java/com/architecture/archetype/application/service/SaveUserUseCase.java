package com.architecture.archetype.application.service;

//import org.springframework.stereotype.Service;

import com.architecture.archetype.domain.driver.gateway.RestrictiveListGateway;
import com.architecture.archetype.domain.error.FoundOnRestrictiveListException;
import com.architecture.archetype.domain.model.UserModel;
import com.architecture.archetype.infrastructure.persistency.repository.UserRepository;

import lombok.RequiredArgsConstructor;

//TODO: son como servicios en mvc, aunque la logica de negocio va dentro del modelo
/**
 * Logica de negocio
 */
//@Service
@RequiredArgsConstructor
public class SaveUserUseCase {

    private final UserRepository userRepository;
    private final RestrictiveListGateway restrictiveListAdapter;

    public UserModel saveUser(UserModel user) {
        if (this.restrictiveListAdapter.getValidate(user.getName())) {
            return userRepository.saveUser(user);
        } else {
            throw new FoundOnRestrictiveListException(user.getName().concat(" se encuentra en lista restrictiva."));
        }
    }
}
