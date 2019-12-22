/**
 * Java candidate interview test.
 * Copyright (c) 2010-2018 Mavenir
 * http://www.mavenir.com
 * All Rights Reserved.
 */
package com.mavenir.interview.measurement.dao.impl;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import com.mavenir.interview.measurement.api.dto.MeasurementDefinionDto;
import com.mavenir.interview.measurement.dao.MeasurementConfigurationDao;

/**
 * Test of {@code MeasurementConfigurationDaoImpl} class.
 */
public class MeasurementConfigurationDaoImplTest {

    private MeasurementConfigurationDao measurementConfigurationDao;

    @Before
    public void setUp() {

        // TODO Init the tested service
        measurementConfigurationDao = new MeasurementConfigurationDaoImpl();
    }

    @Test
    public void testConfigurationDao() {

        // TODO Test getMeasurementDefinitions method

        final List<MeasurementDefinionDto> allMeasurementDefinitions =
                measurementConfigurationDao.getMeasurementDefinitions();

        // TODO Use assertions if possible, e.g.
        assertThat("Result", allMeasurementDefinitions, notNullValue());
    }
}