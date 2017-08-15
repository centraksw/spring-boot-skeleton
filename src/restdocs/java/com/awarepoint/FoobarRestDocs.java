package com.awarepoint;

import com.awarepoint.service.FoobarService;
import com.awarepoint.web.FoobarControllerTestShared;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

/**
 * Generates REST DOCS for Foobar
 */
@Configuration
@RunWith(SpringRunner.class) // needed to use MockBean annotations
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FoobarRestDocs {
    @Rule
    public JUnitRestDocumentation restDocumentation =
            new JUnitRestDocumentation(System.getProperties().getProperty("org.springframework.restdocs.outputDir"));

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private FoobarService foobarService;

    private FoobarControllerTestShared foobarControllerTestShared;

    @Before
    public void setUp() {
        mvc = RestDocUtils.createMockMvc(restDocumentation, context, new ObjectMapper());
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
