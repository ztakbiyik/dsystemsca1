package com.projects.dsystems.repository;

import com.projects.dsystems.model.Customer;
import lombok.SneakyThrows;

import java.util.List;

public interface CrudDOARepository<T> {

    List<T> findAll();

    @SneakyThrows
    T findById(Long id);

    @SneakyThrows
    boolean delete(Long id);

    @SneakyThrows
    T save(T t);
}
