/**
 * Java candidate interview test.
 * Copyright (c) 2010-2018 Mavenir
 * http://www.mavenir.com
 * All Rights Reserved.
 */
package com.mavenir.interview.measurement.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.mavenir.interview.measurement.api.dto.MeasurementDefinionDto;
import com.mavenir.interview.measurement.service.MeasurementConfigurationService;
import com.mavenir.interview.measurement.service.MeasurementExportService;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

/**
 * Implementation of {@code MeasurementExportService}.
 */
public class MeasurementExportServiceImpl implements MeasurementExportService {

    private MeasurementConfigurationService measurementConfigurationService;
    private final String OUTPUT_FILE = "objs.csv";

    @Override
    public void setMeasurementConfigurationService(MeasurementConfigurationService measurementConfigurationService) {
        this.measurementConfigurationService = measurementConfigurationService;
    }

    @Override
    public void exportMeasurements(File outputFolder, List<String> selectedMeasurements) {
        final List<MeasurementDefinionDto> measurementDefinitions =
                measurementConfigurationService.getMeasurementDefinitions(selectedMeasurements);
        try (Writer writer = Files.newBufferedWriter(Paths.get(outputFolder.toString(), this.OUTPUT_FILE))) {
            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();
            beanToCsv.write(measurementDefinitions);
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException x) {
            throw new IllegalArgumentException("Error in CSV Parsing: ", x);
        }

    }
}
