/**
 * Java candidate interview test.
 * Copyright (c) 2010-2018 Mavenir
 * http://www.mavenir.com
 * All Rights Reserved.
 */
package com.mavenir.interview.measurement.dao;

import java.util.List;

import com.mavenir.interview.measurement.api.dto.MeasurementDefinionDto;

/**
 * Measurement configuration DAO (Data Access Object).
 */
public interface MeasurementConfigurationDao {

    /**
     * Get list of {@code MeasurementDefinionDto} beans.
     */
    List<MeasurementDefinionDto> getMeasurementDefinitions();
}
