package com.awarepoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoobarRepository extends JpaRepository<Foobar, Integer> {
}
