/**
 * Java candidate interview test.
 * Copyright (c) 2010-2018 Mavenir
 * http://www.mavenir.com
 * All Rights Reserved.
 */
package com.mavenir.interview.measurement.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.mavenir.interview.measurement.api.dto.MeasurementDefinionDto;
import com.mavenir.interview.measurement.dao.MeasurementConfigurationDao;
import com.mavenir.interview.measurement.service.MeasurementConfigurationService;

/**
 * Implementation of {@code MeasurementExportService}.
 */
public class MeasurementConfigurationServiceImpl implements MeasurementConfigurationService {

    private MeasurementConfigurationDao measurementConfigurationDao;

    @Override
    public void setMeasurementConfigurationDao(MeasurementConfigurationDao measurementConfigurationDao) {
        this.measurementConfigurationDao = measurementConfigurationDao;
    }

    @Override
    public List<MeasurementDefinionDto> getMeasurementDefinitions(List<String> selectedMeasurements) {
        final List<MeasurementDefinionDto> measurementDefinitions =
                measurementConfigurationDao.getMeasurementDefinitions();
        // TODO Provide filtered list of measurements  -> DONE
        Set<MeasurementDefinionDto> streamSet = new HashSet<>();
        for(String str: selectedMeasurements){
            List<MeasurementDefinionDto> list = new ArrayList<>(measurementDefinitions);
            streamSet.addAll(list.stream().filter((n -> (n.toString().contains(str)))).collect(Collectors.toList()));
        }
        for(MeasurementDefinionDto obj : streamSet){
            System.out.println(obj.getAppName());
        }
        return new ArrayList<>(streamSet);
    }
}
