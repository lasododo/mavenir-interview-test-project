/**
 * Java candidate interview test.
 * Copyright (c) 2010-2018 Mavenir
 * http://www.mavenir.com
 * All Rights Reserved.
 */
package com.mavenir.interview.measurement.service.impl;

import java.util.ArrayList;
import java.util.List;

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

    @Before
    public void setUp() {

        // TODO The DAO can be mocked or use different implementation
        measurementConfigurationDao = new MeasurementConfigurationDaoImpl();

        measurementConfigurationService = new MeasurementConfigurationServiceImpl();
        measurementConfigurationService.setMeasurementConfigurationDao(measurementConfigurationDao);
    }

    @Test
    public void testConfigurationService() {

        // TODO Test getMeasurementDefinitions method
        final List<MeasurementDefinionDto> filteredMeasurementDefinitions =
                measurementConfigurationService.getMeasurementDefinitions(new ArrayList<>());

        // TODO Use assertions if possible, e.g.
        assertThat("Result", filteredMeasurementDefinitions, notNullValue());
    }
}