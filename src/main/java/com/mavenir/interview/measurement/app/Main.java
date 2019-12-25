/**
 * Java candidate interview test.
 * Copyright (c) 2010-2018 Mavenir
 * http://www.mavenir.com
 * All Rights Reserved.
 */
package com.mavenir.interview.measurement.app;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.mavenir.interview.measurement.dao.MeasurementConfigurationDao;
import com.mavenir.interview.measurement.dao.impl.MeasurementConfigurationDaoImpl;
import com.mavenir.interview.measurement.service.MeasurementConfigurationService;
import com.mavenir.interview.measurement.service.MeasurementExportService;
import com.mavenir.interview.measurement.service.impl.MeasurementConfigurationServiceImpl;
import com.mavenir.interview.measurement.service.impl.MeasurementExportServiceImpl;

/**
 * Starting point of the Measurement application.
 */
public class Main {

    private MeasurementExportService measurementExportService;
    private MeasurementConfigurationService measurementConfigurationService;
    private MeasurementConfigurationDao measurementConfigurationDao;

    /*
        Ignore this code -> Work in progress for later
            ( If you are reading this, I have forgotten to remove it
                 before evaluation / I am working on it right now )

        --> this code should not work, because of main is static.

        private String path;

        public void setPath(String path) {
            this.path = path;
        }

        private void initCustom() {
            MeasurementConfigurationDaoImpl editedMCDI = new MeasurementConfigurationDaoImpl();
            MeasurementConfigurationServiceImpl editedMCSI = new MeasurementConfigurationServiceImpl();
            MeasurementExportServiceImpl editedMESI = new MeasurementExportServiceImpl();

            editedMCDI.setFilepath(this.path);
            editedMCSI.setMeasurementConfigurationDao(editedMCDI);
            editedMESI.setMeasurementConfigurationService(editedMCSI);

            this.measurementExportService = editedMESI;
        }
    */

    private void initServices() {
        // Setup concrete implementations
        measurementConfigurationService = new MeasurementConfigurationServiceImpl();
        measurementExportService = new MeasurementExportServiceImpl();
        measurementConfigurationDao = new MeasurementConfigurationDaoImpl();

        // Inject dependencies
        measurementConfigurationService.setMeasurementConfigurationDao(measurementConfigurationDao);
        measurementExportService.setMeasurementConfigurationService(measurementConfigurationService);

        /*
            if (this.path != null) {
                initCustom();
            }
        */
    }

    public void exportMeasurements(File outputFolder, List<String> selectedMeasurements) {
        initServices();
        measurementExportService.exportMeasurements(outputFolder, selectedMeasurements);
    }


    public static void main(String[] args) {
        if (args.length != 2){
            throw new IllegalArgumentException("not enough arguments");
        }

        final Main app = new Main();
        final File outputFolder = new File(args[0]);
        final List<String> selectedMeasurements = Arrays.asList(args[1].split(","));

        app.exportMeasurements(outputFolder, selectedMeasurements);

    }
}
