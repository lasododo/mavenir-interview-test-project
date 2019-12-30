/**
 * Java candidate interview test.
 * Copyright (c) 2010-2018 Mavenir
 * http://www.mavenir.com
 * All Rights Reserved.
 */
package com.mavenir.interview.measurement.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.mavenir.interview.measurement.api.dto.MeasurementDefinionDto;
import com.mavenir.interview.measurement.service.MeasurementConfigurationService;
import com.mavenir.interview.measurement.service.MeasurementExportService;
import com.opencsv.CSVWriter;

/**
 * Implementation of {@code MeasurementExportService}.
 */
public class MeasurementExportServiceImpl implements MeasurementExportService {

    private MeasurementConfigurationService measurementConfigurationService;
    private int timer = 10000;

    /**
     * DO NOT USE THIS !!! ITS FOR TEST PURPOSES ONLY!!!
     * @param timer -> integer that represents time in milliseconds ( 1000 == 1 second )
     */
    public void setTimer(int timer) {
        this.timer = timer;
    }

    @Override
    public void setMeasurementConfigurationService(MeasurementConfigurationService measurementConfigurationService) {
        this.measurementConfigurationService = measurementConfigurationService;
    }

    /**
     * exports a single measurement.
     * @param outputFolder -> folder, where to output the CSV
     * @param msr -> MeasurementDefinionDto object that contains all info thats need to ne written
     */
    private void cvsWriter(File outputFolder, MeasurementDefinionDto msr){
        try (
                Writer writer = new FileWriter(
                        String.valueOf(Paths.get(outputFolder.toString(),
                                msr.getAppName() + "-" + msr.getMeasurementId() + ".csv")), true
                );
                CSVWriter csvWriter = new CSVWriter(writer)
        ) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            csvWriter.writeNext(new String[]{
                    formatter.format(date),
                    String.valueOf(msr.getInitialValue()),
                    msr.getAppName(),
                    msr.getMeasurementId()
            });
            msr.multiplyInit(msr.getIncrement());
        } catch (IOException xx) {
            throw new IllegalArgumentException("Error in CSV Parsing: ", xx);
        }
    }

    @Override
    public void exportMeasurements(File outputFolder, List<String> selectedMeasurements) {
        final List<MeasurementDefinionDto> measurementDefinitions =
                measurementConfigurationService.getMeasurementDefinitions(selectedMeasurements);

        if (measurementDefinitions.isEmpty()){
            return;
        }

        int loopEnd = 0;
        boolean loopHandler = true;
        while(loopHandler) {
            loopHandler = false;
            long start = System.currentTimeMillis();
            for (MeasurementDefinionDto msr : measurementDefinitions) {
                if (msr.getInterval() > loopEnd) {
                    loopHandler = true;
                }
                cvsWriter(outputFolder, msr);
            }
            loopEnd += 10;
            long end = System.currentTimeMillis();
            try {
                if(loopHandler) {
                    // just check -> System.out.println(this.timer - (end - start));
                    Thread.sleep(this.timer - (end - start));
                }
            } catch (InterruptedException x){
                throw new IllegalArgumentException("Interrupted");
            }
        }
    }
}
