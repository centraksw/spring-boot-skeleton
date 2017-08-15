package com.awarepoint.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Foobar entity, used both in the persistence layer, and in the REST API layer
 */
@Entity
public class Foobar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int value;

    /**
     * The unique identifier of foobar
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * The value of foobar
     */
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Foobar)) return false;
        Foobar foobar = (Foobar) o;
        return id == foobar.id &&
                value == foobar.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value);
    }

    @Override
    public String toString() {
        return "Foobar{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }
}
