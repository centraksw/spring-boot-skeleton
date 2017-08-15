package com.awarepoint.web;

import com.awarepoint.service.FoobarService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Unit test for FoobarController
 */
@RunWith(SpringRunner.class) // needed to use MockBean annotations
@WebMvcTest(FoobarController.class)
public class FoobarControllerTest {

    @Autowired
    private MockMvc mvc;

    // MockBean will magically replace the DefaultFoobarService
    @MockBean
    private FoobarService foobarService;

    private FoobarControllerTestShared foobarControllerTestShared;

    @Before
    public void setUp() {
        foobarControllerTestShared = new FoobarControllerTestShared(mvc, foobarService);
    }

    @Test
    public void getAll() throws Exception {
        foobarControllerTestShared.getAll();
    }

    @Test
    public void getOne() throws Exception {
        foobarControllerTestShared.getOne();
    }

    @Test
    public void create() throws Exception {
        foobarControllerTestShared.create();
    }

    @Test
    public void delete() throws Exception {
        foobarControllerTestShared.delete();
    }
}