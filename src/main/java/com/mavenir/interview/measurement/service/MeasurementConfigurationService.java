/**
 * Java candidate interview test.
 * Copyright (c) 2010-2018 Mavenir
 * http://www.mavenir.com
 * All Rights Reserved.
 */
package com.mavenir.interview.measurement.service;

import java.util.List;

import com.mavenir.interview.measurement.api.dto.MeasurementDefinionDto;
import com.mavenir.interview.measurement.dao.MeasurementConfigurationDao;

/**
 * Measurement configuration provider service.
 */
public interface MeasurementConfigurationService {

    /**
     * Inject {@code MeasurementConfigurationDao} DAO service.
     */
    void setMeasurementConfigurationDao(MeasurementConfigurationDao measurementConfigurationDao);

    /**
     * Get filtered list of measurements
     *
     * @param selectedMeasurements List of selected measurements
     */
    List<MeasurementDefinionDto> getMeasurementDefinitions(List<String> selectedMeasurements);
}
