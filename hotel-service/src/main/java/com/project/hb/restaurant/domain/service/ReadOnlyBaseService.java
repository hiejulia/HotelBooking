package com.project.hb.restaurant.domain.service;

import com.project.hb.restaurant.domain.repository.ReadOnlyRepository;

public abstract class ReadOnlyBaseService<TE, T> {

    private ReadOnlyRepository<TE, T> repository;

    ReadOnlyBaseService(ReadOnlyRepository<TE, T> repository) {
        this.repository = repository;
    }
}
