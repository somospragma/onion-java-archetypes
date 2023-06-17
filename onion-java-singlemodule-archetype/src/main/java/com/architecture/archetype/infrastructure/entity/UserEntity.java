package com.architecture.archetype.infrastructure.entity;

import java.io.Serial;
import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Entidad encargada de persistir la informacion en la BD relacionada al USER
 */
@Entity
@Setter
@Getter
@Table(name = "users")
public class UserEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 6230110001705013136L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "password", nullable = false)
    private String password;
}
