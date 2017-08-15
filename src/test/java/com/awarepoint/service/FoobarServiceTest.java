package com.awarepoint.service;

import com.awarepoint.domain.Foobar;
import com.awarepoint.repository.FoobarRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;

@RunWith(MockitoJUnitRunner.class) //eliminates needing to MockitoAnnotations.initMocks()
public class FoobarServiceTest {

    @Mock
    private FoobarRepository foobarRepository;
    private FoobarService foobarService;

    @Before
    public void setup() {
        foobarService = new DefaultFoobarService(foobarRepository);
    }

    @Test
    public void get() {
        Foobar foobar = new Foobar();
        foobar.setId(1);
        foobar.setValue(999);

        Mockito.when(foobarRepository.findOne(1)).thenReturn(foobar);
        Assert.assertEquals(foobar, foobarService.get(1));
    }

    @Test
    public void getAll() {
        Foobar foobar1 = new Foobar();
        foobar1.setId(1);
        foobar1.setValue(999);

        Foobar foobar2 = new Foobar();
        foobar1.setId(2);
        foobar1.setValue(1000);

        List<Foobar> expectedList = Arrays.asList(foobar1, foobar2);

        Mockito.when(foobarRepository.findAll()).thenReturn(expectedList);

        Assert.assertThat(foobarService.getAll(), IsIterableContainingInAnyOrder.containsInAnyOrder(foobar1, foobar2));
    }

    @Test
    public void save() {
        Foobar foobarBefore = new Foobar();
        foobarBefore.setId(0);
        foobarBefore.setValue(999);

        Foobar foobarAfter = new Foobar();
        foobarAfter.setId(1);
        foobarAfter.setValue(999);

        Mockito.when(foobarRepository.save(foobarBefore)).thenReturn(foobarAfter);
        Assert.assertEquals(foobarService.save(foobarBefore), foobarAfter);
    }

    @Test
    public void delete() {
        foobarService.delete(1);
        Mockito.verify(foobarRepository, Mockito.times(1)).delete(1);
    }
}