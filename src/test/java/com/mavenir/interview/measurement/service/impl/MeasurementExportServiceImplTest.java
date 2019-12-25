/**
 * Java candidate interview test.
 * Copyright (c) 2010-2018 Mavenir
 * http://www.mavenir.com
 * All Rights Reserved.
 */
package com.mavenir.interview.measurement.service.impl;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mavenir.interview.measurement.dao.impl.MeasurementConfigurationDaoImpl;
import com.mavenir.interview.measurement.service.MeasurementConfigurationService;

/**
 * Test of {@code MeasurementExportServiceImpl} class.
 */
public class MeasurementExportServiceImplTest {

    private MeasurementExportServiceImpl measurementExportService;
    private String folderPath = "src/test/resources/export";

    private void reader1(File f) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)))){
            String line = reader.readLine();
            line = reader.readLine();
            if (line != null) {
                Assert.assertTrue(line.contains("perf.int000"));
                return;
            }
            Assert.fail();
        } catch (IOException x) {
            throw new IllegalArgumentException("Tests failed on file handling: ", x);
        }
    }

    private void firstLineChecker(File f) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)))){
            String line = reader.readLine();
            if (line != null) {
                Assert.assertEquals(line,
                        "appName,classId,increment,initialValue,instanceId,interval,measurementId");
                return;
            }
            Assert.fail();
        } catch (IOException x) {
            throw new IllegalArgumentException("Tests failed on file handling: ", x);
        }
    }

    private void reader2(File f) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)))){
            String line = reader.readLine();
            Assert.assertNull(line);
        } catch (IOException x) {
            throw new IllegalArgumentException("Tests failed on file handling: ", x);
        }
    }

    @Before
    public void setUp() {
        MeasurementConfigurationService measurementConfigurationService = new MeasurementConfigurationServiceImpl();
        measurementConfigurationService.setMeasurementConfigurationDao(new MeasurementConfigurationDaoImpl());
        this.measurementExportService = new MeasurementExportServiceImpl();
        this.measurementExportService.setMeasurementConfigurationService(measurementConfigurationService);
    }

    @Test
    public void testExportService() {
        measurementExportService.exportMeasurements(new File("."), new ArrayList<>());
    }

    @Test
    public void testExportService1() {
        List<String> lst = new ArrayList<>();
        lst.add("perf.int000");
        this.measurementExportService.exportMeasurements(new File(this.folderPath), lst);
        File f = new File(String.valueOf(Paths.get(this.folderPath, this.measurementExportService.getOUTPUT_FILE())));
        reader1(f);
        if(!f.delete()){
            Assert.fail();
        }
    }

    @Test
    public void testExportService2() {
        List<String> lst = new ArrayList<>();
        lst.add("perf.int000");
        this.measurementExportService.exportMeasurements(new File(this.folderPath), lst);
        File f = new File(String.valueOf(Paths.get(this.folderPath, this.measurementExportService.getOUTPUT_FILE())));
        firstLineChecker(f);
        if(!f.delete()){
            Assert.fail();
        }
    }

    @Test
    public void testEmptyExportService1() {
        this.measurementExportService.exportMeasurements(new File(this.folderPath), new ArrayList<>());
        File f = new File(String.valueOf(Paths.get(this.folderPath, this.measurementExportService.getOUTPUT_FILE())));
        reader2(f);
        if(!f.delete()){
            Assert.fail();
        }
    }

    @Test
    public void testEmptyExportService2() {
        List<String> lst = new ArrayList<>();
        lst.add("whatever thingy");
        this.measurementExportService.exportMeasurements(new File(this.folderPath), lst);
        File f = new File(String.valueOf(Paths.get(this.folderPath, this.measurementExportService.getOUTPUT_FILE())));
        reader2(f);
        if(!f.delete()){
            Assert.fail();
        }
    }
}