package com.example.cqrs_premium.domain.model;

import lombok.Getter;

@Getter
public class Customer {
    private final Long id;
    private final String name;
    private final String email;

    public Customer(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}