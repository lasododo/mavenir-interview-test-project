/**
 * Java candidate interview test.
 * Copyright (c) 2010-2018 Mavenir
 * http://www.mavenir.com
 * All Rights Reserved.
 */
package com.mavenir.interview.measurement.service;

import java.io.File;
import java.util.List;

/**
 * Measurement export service.
 */
public interface MeasurementExportService {

    /**
     * Inject {@code MeasurementConfigurationService} service.
     */
    void setMeasurementConfigurationService(MeasurementConfigurationService measurementConfigurationService);

    /**
     * Create measurement representations, e.g. files.
     *
     * @param outputFolder Folder where to export the files
     * @param selectedMeasurements List of selected measurement codes
     */
    void exportMeasurements(File outputFolder, List<String> selectedMeasurements);
}
