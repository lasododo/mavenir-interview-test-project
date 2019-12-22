/**
 * Java candidate interview test.
 * Copyright (c) 2010-2018 Mavenir
 * http://www.mavenir.com
 * All Rights Reserved.
 */
package com.mavenir.interview.measurement.service.impl;

import java.io.File;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.mavenir.interview.measurement.dao.MeasurementConfigurationDao;
import com.mavenir.interview.measurement.dao.impl.MeasurementConfigurationDaoImpl;
import com.mavenir.interview.measurement.service.MeasurementConfigurationService;
import com.mavenir.interview.measurement.service.MeasurementExportService;

/**
 * Test of {@code MeasurementExportServiceImpl} class.
 */
public class MeasurementExportServiceImplTest {

    private MeasurementConfigurationDao measurementConfigurationDao;
    private MeasurementConfigurationService measurementConfigurationService;
    private MeasurementExportService measurementExportService;

    @Before
    public void setUp() {

        // TODO The DAO can be mocked or use different implementation
        measurementConfigurationDao = new MeasurementConfigurationDaoImpl();

        // TODO The service can be mocked or use different implementation
        measurementConfigurationService = new MeasurementConfigurationServiceImpl();
        measurementConfigurationService.setMeasurementConfigurationDao(measurementConfigurationDao);

        measurementExportService = new MeasurementExportServiceImpl();
        measurementExportService.setMeasurementConfigurationService(measurementConfigurationService);
    }

    @Test
    public void testExportService() {

        // TODO Test exportMeasurements method
        measurementExportService.exportMeasurements(new File("."), new ArrayList<>());
    }
}