package com.example.sgpa.domain.usecases.utils;

import java.util.List;
import java.util.Optional;

public interface DAO<T,K> {
    K create(T obj);
    Optional<T> findOne(K type);
    List<T> findAll();
    void update(T obj);
    boolean delete(T obj);
}
