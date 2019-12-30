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
import com.mavenir.interview.measurement.dao.impl.MeasurementConfigurationDaoImpl;
import com.mavenir.interview.measurement.service.MeasurementConfigurationService;

/**
 * Test of {@code MeasurementConfigurationServiceImpl} class.
 */
public class MeasurementConfigurationServiceImplTest {

    private MeasurementConfigurationService measurementConfigurationService;
    private MeasurementConfigurationDaoImpl impl;


    @Before
    public void setUp() {
        this.measurementConfigurationService = new MeasurementConfigurationServiceImpl();
        this.measurementConfigurationService.setMeasurementConfigurationDao(new MeasurementConfigurationDaoImpl());
        this.impl = new MeasurementConfigurationDaoImpl();
    }

    @Test
    public void testConfigurationService() {
        final List<MeasurementDefinionDto> filteredMeasurementDefinitions =
                this.measurementConfigurationService.getMeasurementDefinitions(new ArrayList<>());

        assertThat("Result", filteredMeasurementDefinitions, notNullValue());
    }

    @Test
    public void testFilteringIDs() {
        String filepath = "src/test/resources/test_measurement_config.json";
        this.impl.setFilepath(filepath);
        this.measurementConfigurationService.setMeasurementConfigurationDao(this.impl);

        List<String> lst = new ArrayList<>();
        lst.add("TEST_APP");
        List<MeasurementDefinionDto> ret = this.measurementConfigurationService.getMeasurementDefinitions(lst);
        Assert.assertEquals(0, ret.size());
        lst.clear();
        lst.add("perf.int000");
        ret = this.measurementConfigurationService.getMeasurementDefinitions(lst);
        Assert.assertEquals(1, ret.size());
        lst.clear();
        lst.add("responder");
        ret = this.measurementConfigurationService.getMeasurementDefinitions(lst);
        Assert.assertEquals(0, ret.size());
        lst.clear();
        lst.add("responder-00");
        ret = this.measurementConfigurationService.getMeasurementDefinitions(lst);
        Assert.assertEquals(0, ret.size());
        lst.clear();

    }

    @Test
    public void multipleFiltersCheck() {
        String filepath = "src/test/resources/test_measurement_config.json";
        this.impl.setFilepath(filepath);
        this.measurementConfigurationService.setMeasurementConfigurationDao(this.impl);
        List<MeasurementDefinionDto> ret;

        List<String> lst = new ArrayList<>();
        lst.add("perf.int000");
        lst.add("responder");
        ret = this.measurementConfigurationService.getMeasurementDefinitions(lst);
        Assert.assertEquals(1, ret.size());
        lst.clear();
        lst.add("perf.int000");
        lst.add("responder");
        lst.add("what have you broke ?");
        ret = this.measurementConfigurationService.getMeasurementDefinitions(lst);
        Assert.assertEquals(1, ret.size());
        lst.clear();
        lst.add("resp");
        lst.add("rapp");
        lst.add("wot");
        ret = this.measurementConfigurationService.getMeasurementDefinitions(lst);
        Assert.assertEquals(0, ret.size());
        lst.clear();
    }

    @Test
    public void testNoEquals() {
        String filepath = "src/test/resources/test_measurement_config.json";
        this.impl.setFilepath(filepath);
        this.measurementConfigurationService.setMeasurementConfigurationDao(this.impl);
        List<MeasurementDefinionDto> ret;
        List<String> lst = new ArrayList<>();

        lst.add("random");
        ret = this.measurementConfigurationService.getMeasurementDefinitions(lst);
        Assert.assertEquals(0, ret.size());
        lst.clear();
        lst.add("should not work at all");
        ret = this.measurementConfigurationService.getMeasurementDefinitions(lst);
        Assert.assertEquals(0, ret.size());
        lst.clear();
        lst.add("what have you broke ?");
        ret = this.measurementConfigurationService.getMeasurementDefinitions(lst);
        Assert.assertEquals(0, ret.size());
        lst.clear();
    }
}