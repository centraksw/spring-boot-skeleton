package com.awarepoint.service;

import com.awarepoint.repository.Foobar;
import com.awarepoint.repository.FoobarRepository;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

@Service
public class FoobarService {

    @Resource
    private FoobarRepository foobarRepository;


    public Foobar get(int id) {
        return foobarRepository.findOne(id);
    }

    public List<Foobar> getAll() {
        return foobarRepository.findAll();
    }

    public Foobar save(Foobar foobar) {
        return foobarRepository.save(foobar);
    }

    public void delete(int id) {
        foobarRepository.delete(id);
    }
}
