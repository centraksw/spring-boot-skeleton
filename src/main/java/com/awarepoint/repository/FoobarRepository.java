package com.awarepoint.repository;

import com.awarepoint.domain.Foobar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Persistence layer for Foobar.
 */
@Repository
public interface FoobarRepository extends JpaRepository<Foobar, Integer> {
}
