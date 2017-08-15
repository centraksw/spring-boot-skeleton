package com.awarepoint.web;

import com.awarepoint.domain.Foobar;
import com.awarepoint.service.FoobarService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.hamcrest.Matchers;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

/**
 * There's significant overlap between FoobarRestDocs and FoobarControllerTest, but they span different concerns.
 * This class holds the common code between those two classes.
 */
public class FoobarControllerTestShared {

    private final MockMvc mvc;
    private final FoobarService foobarService;

    public FoobarControllerTestShared(MockMvc mvc, FoobarService foobarService) {
        this.mvc = mvc;
        this.foobarService = foobarService;
    }

    public void getAll() throws Exception {
        Foobar foobar1 = new Foobar();
        foobar1.setId(1);
        foobar1.setValue(999);

        Foobar foobar2 = new Foobar();
        foobar2.setId(2);
        foobar2.setValue(1000);

        List<Foobar> expectedList = Arrays.asList(foobar1, foobar2);

        Mockito.when(foobarService.getAll()).thenReturn(expectedList);

        mvc.perform(MockMvcRequestBuilders.get("/foobar")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(expectedList.size())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(foobar1.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].value", Matchers.is(foobar1.getValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(foobar2.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].value", Matchers.is(foobar2.getValue())));
    }

    public void getOne() throws Exception {
        Foobar foobar1 = new Foobar();
        foobar1.setId(1);
        foobar1.setValue(999);

        Mockito.when(foobarService.get(1)).thenReturn(foobar1);

        // confirm that 404 is returned for a wrong id
        mvc.perform(MockMvcRequestBuilders.get("/foobar/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        // confirm 200 is returned with the Foobar that we expect
        mvc.perform(MockMvcRequestBuilders.get("/foobar/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(foobar1.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.value", Matchers.is(foobar1.getValue())));
    }

    public void create() throws Exception {
        Foobar foobarBefore = new Foobar();
        foobarBefore.setId(0);
        foobarBefore.setValue(999);

        Foobar foobarAfter = new Foobar();
        foobarAfter.setId(1);
        foobarAfter.setValue(999);

        Mockito.when(foobarService.save(org.mockito.Matchers.argThat(new FooBarMatcher(foobarBefore.getId(),
                foobarBefore.getValue())))).thenReturn(foobarAfter);

        // confirm 200 is returned with the Foobar that we expect
        mvc.perform(MockMvcRequestBuilders.post("/foobar/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToJson(foobarBefore)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(foobarAfter.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.value", Matchers.is(foobarAfter.getValue())));

        // test update
        Foobar foobarUpdate = new Foobar();
        foobarUpdate.setId(1);
        foobarUpdate.setValue(1000);

        Mockito.when(foobarService.save(org.mockito.Matchers.argThat(new FooBarMatcher(foobarUpdate.getId(),
                foobarUpdate.getValue())))).thenReturn(foobarUpdate);

        // confirm 200 is returned with the Foobar that we expect
        mvc.perform(MockMvcRequestBuilders.post("/foobar/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToJson(foobarUpdate)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(foobarUpdate.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.value", Matchers.is(foobarUpdate.getValue())));
    }

    public void delete() throws Exception {
        // confirm that 404 is returned for a wrong id
        Mockito.doThrow(new EmptyResultDataAccessException(1)).when(foobarService).delete(2);
        mvc.perform(MockMvcRequestBuilders.delete("/foobar/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        // confirm 200 is returned when we delete with the right id
        mvc.perform(MockMvcRequestBuilders.delete("/foobar/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private String objectToJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    /**
     * Helper class for Mockito matching of FooBar objects
     */
    private class FooBarMatcher extends ArgumentMatcher<Foobar> {
        final int id;
        final int value;

        public FooBarMatcher(int id, int value) {
            this.id = id;
            this.value = value;
        }

        @Override
        public boolean matches(Object argument) {
            if (argument == null) return false;
            Foobar foobar = (Foobar) argument;
            return (foobar.getId() == id) && (foobar.getValue() == value);
        }
    }
}
