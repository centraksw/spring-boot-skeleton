package com.awarepoint.web;

import com.awarepoint.domain.Foobar;
import com.awarepoint.service.FoobarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/foobar")
@RestController
public class FoobarController {

    @Autowired
    private FoobarService foobarService;

    /**
     * Gets all foobars
     * @return list of all foobars
     */
    @GetMapping
    List<Foobar> getAll() {
        return foobarService.getAll();
    }

    /**
     * Retrieve foobar by id
     * @param id id of the foobar to get
     * @return foobar if found, otherwise a 404
     */
    @GetMapping("/{id}")
    Foobar getOne(@PathVariable int id) {
        Foobar foobar = foobarService.get(id);
        if (foobar == null) {
            throw new NoSuchEntityIdException();
        }
        return foobar;
    }

    /**
     * Save or update foobar. id = 0 implies create, and the object return will have the id populated
     * If id != 0, then it's an update; the object specified by this id will be updated
     * @param foobar to create or update
     * @return foobar created
     */
    @PostMapping
    Foobar create(@RequestBody Foobar foobar) {
        return foobarService.save(foobar);
    }

    /**
     * Delete a foobar based on id
     * @param id of foobar to delete. 200 if successful, otherwise a 404 if not found
     */
    @DeleteMapping("/{id}")
    void delete(@PathVariable int id) {
        try {
            foobarService.delete(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NoSuchEntityIdException();
        }
    }
}
