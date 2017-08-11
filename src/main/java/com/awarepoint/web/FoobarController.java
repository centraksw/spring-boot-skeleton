package com.awarepoint.web;

import com.awarepoint.repository.Foobar;
import com.awarepoint.service.FoobarService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.annotation.Resource;

@RequestMapping("/foobar")
@RestController
public class FoobarController {

    @Resource
    private FoobarService foobarService;

    @GetMapping
    List<Foobar> getAll() {
        return foobarService.getAll();
    }

    @GetMapping("/{id}")
    Foobar getOne(@PathVariable int id) {
        return foobarService.get(id);
    }

    @PostMapping
    Foobar create(@RequestBody Foobar foobar) {
        return foobarService.save(foobar);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable int id) {
        foobarService.delete(id);
    }
}
