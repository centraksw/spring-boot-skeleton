package com.awarepoint.service;

import com.awarepoint.domain.Foobar;

import java.util.List;

/**
 * Service for Create/Update/Delete/Query operation on Foobar
 */
public interface FoobarService {

    /**
     * Retrieves a foobar based on id
     * @param id of foobar
     * @return foobar
     */
    Foobar get(int id);

    /**
     * Retrieves all fobars
     * @return list of foobars
     */
    List<Foobar> getAll();

    /**
     * Save or update foobar. id = 0 implies create, and the object return will have the id populated
     * If id != 0, then it's an update; the object specified by this id will be updated
     * @param foobar to create or update
     * @return foobar created
     */
    Foobar save(Foobar foobar);

    /**
     * Delete a foobar based on id
     * @param id of foobar to delete
     */
    void delete(int id);
}
