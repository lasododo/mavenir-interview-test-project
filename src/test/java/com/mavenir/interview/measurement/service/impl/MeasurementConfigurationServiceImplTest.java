/**
 * Java candidate interview test.
 * Copyright (c) 2010-2018 Mavenir
 * http://www.mavenir.com
 * All Rights Reserved.
 */
package com.mavenir.interview.measurement.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import com.mavenir.interview.measurement.api.dto.MeasurementDefinionDto;
import com.mavenir.interview.measurement.dao.MeasurementConfigurationDao;
import com.mavenir.interview.measurement.dao.impl.MeasurementConfigurationDaoImpl;
import com.mavenir.interview.measurement.service.MeasurementConfigurationService;

/**
 * Test of {@code MeasurementConfigurationServiceImpl} class.
 */
public class MeasurementConfigurationServiceImplTest {

    private MeasurementConfigurationService measurementConfigurationService;
    private MeasurementConfigurationDao measurementConfigurationDao;
    private List<MeasurementDefinionDto> allMD;
    private MeasurementConfigurationDaoImpl impl;


    @Before
    public void setUp() {

        // TODO The DAO can be mocked or use different implementation
        measurementConfigurationDao = new MeasurementConfigurationDaoImpl();

        measurementConfigurationService = new MeasurementConfigurationServiceImpl();
        measurementConfigurationService.setMeasurementConfigurationDao(measurementConfigurationDao);

        this.allMD = measurementConfigurationDao.getMeasurementDefinitions();
        this.impl = new MeasurementConfigurationDaoImpl();
    }

    @Test
    public void testConfigurationService() {

        // TODO Test getMeasurementDefinitions method
        final List<MeasurementDefinionDto> filteredMeasurementDefinitions =
                measurementConfigurationService.getMeasurementDefinitions(new ArrayList<>());

        // TODO Use assertions if possible, e.g.
        assertThat("Result", filteredMeasurementDefinitions, notNullValue());
    }

    @Test
    public void testFilteringIDs() {
        String filepath = "src/test/resources/test_measurement_config.json";
        this.impl.setFilepath(filepath);
        measurementConfigurationService.setMeasurementConfigurationDao(this.impl);

        List<String> lst = new ArrayList<>();
        lst.add("TEST_APP");
        List<MeasurementDefinionDto> ret = measurementConfigurationService.getMeasurementDefinitions(lst);
        Assert.assertEquals(1, ret.size());
        lst.clear();
        lst.add("perf.int000");
        ret = measurementConfigurationService.getMeasurementDefinitions(lst);
        Assert.assertEquals(1, ret.size());
        lst.clear();
        lst.add("responder");
        ret = measurementConfigurationService.getMeasurementDefinitions(lst);
        Assert.assertEquals(1, ret.size());
        lst.clear();
        lst.add("responder-00");
        ret = measurementConfigurationService.getMeasurementDefinitions(lst);
        Assert.assertEquals(1, ret.size());
        lst.clear();

    }

    @Test
    public void multipleFiltersCheck() {
        String filepath = "src/test/resources/test_measurement_config.json";
        this.impl.setFilepath(filepath);
        measurementConfigurationService.setMeasurementConfigurationDao(this.impl);
        List<MeasurementDefinionDto> ret;

        List<String> lst = new ArrayList<>();
        lst.add("perf.int000");
        lst.add("responder");
        ret = measurementConfigurationService.getMeasurementDefinitions(lst);
        Assert.assertEquals(1, ret.size());
        lst.clear();
        lst.add("perf.int000");
        lst.add("responder");
        lst.add("what have you broke ?");
        ret = measurementConfigurationService.getMeasurementDefinitions(lst);
        Assert.assertEquals(1, ret.size());
        lst.clear();
        lst.add("resp");
        lst.add("rapp");
        lst.add("wot");
        ret = measurementConfigurationService.getMeasurementDefinitions(lst);
        Assert.assertEquals(0, ret.size());
        lst.clear();
    }

    @Test
    public void testNoEquals() {
        String filepath = "src/test/resources/test_measurement_config.json";
        this.impl.setFilepath(filepath);
        measurementConfigurationService.setMeasurementConfigurationDao(this.impl);
        List<MeasurementDefinionDto> ret;
        List<String> lst = new ArrayList<>();

        lst.add("random");
        ret = measurementConfigurationService.getMeasurementDefinitions(lst);
        Assert.assertEquals(0, ret.size());
        lst.clear();
        lst.add("should not work at all");
        ret = measurementConfigurationService.getMeasurementDefinitions(lst);
        Assert.assertEquals(0, ret.size());
        lst.clear();
        lst.add("what have you broke ?");
        ret = measurementConfigurationService.getMeasurementDefinitions(lst);
        Assert.assertEquals(0, ret.size());
        lst.clear();
    }
}