package com.architecture.archetype.infrastructure.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.architecture.archetype.application.factory.FactoryUser;
import com.architecture.archetype.application.handler.HandlerSaveUser;
import com.architecture.archetype.application.service.SaveUserUseCase;
import com.architecture.archetype.domain.driver.gateway.RestrictiveListGateway;
import com.architecture.archetype.domain.driver.repository.UserRepository;
import com.architecture.archetype.infrastructure.adapter.RestrictiveListTransunionAdapter;
import com.architecture.archetype.infrastructure.persistency.dao.UserDao;
import com.architecture.archetype.infrastructure.persistency.factory.FactoryUserEntity;
import com.architecture.archetype.infrastructure.persistency.repository.UserRepositoryImpl;

@Configuration
@ComponentScan(basePackages = {"com.kiire.onboarding"})
public class BeanConfiguration {

    @Bean
    public FactoryUserEntity factoryUserEntity() {
        return new FactoryUserEntity();
    }

    @Bean
    public FactoryUser factoryUser() {
        return new FactoryUser();
    }

    @Bean
    public HandlerSaveUser handlerSaveUser(SaveUserUseCase saveUserUseCase, FactoryUser factoryUser) {
        return new HandlerSaveUser(saveUserUseCase, factoryUser);
    }

    @Bean
    public RestrictiveListGateway restrictiveListAdapter() {
        return new RestrictiveListTransunionAdapter();
    }

    @Bean
    public UserRepository userRepository(UserDao userDao, FactoryUserEntity factoryUserEntity) {
        return new UserRepositoryImpl(userDao, factoryUserEntity);
    }

    @Bean
    public SaveUserUseCase saveUserUseCase(UserRepository userRepository, RestrictiveListGateway restrictiveListAdapter) {
        return new SaveUserUseCase(userRepository, restrictiveListAdapter);
    }

}
