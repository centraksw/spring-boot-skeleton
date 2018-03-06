package com.awarepoint.service;

import com.awarepoint.domain.Foobar;
import com.awarepoint.repository.FoobarRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DefaultFoobarService implements FoobarService{

    private final FoobarRepository foobarRepository;

    public DefaultFoobarService(FoobarRepository foobarRepository) {
        this.foobarRepository = foobarRepository;
    }

    public Foobar get(int id) {
        return foobarRepository.getOne(id);
    }

    public List<Foobar> getAll() {
        return foobarRepository.findAll();
    }

    public Foobar save(Foobar foobar) {
        return foobarRepository.save(foobar);
    }

    public void delete(int id) {
        foobarRepository.deleteById(id);
    }
}
