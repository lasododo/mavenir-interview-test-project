/**
 * Java candidate interview test.
 * Copyright (c) 2010-2018 Mavenir
 * http://www.mavenir.com
 * All Rights Reserved.
 */
package com.mavenir.interview.measurement.service.impl;

import java.io.*;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;

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
            if (line != null) {
                Assert.assertTrue(line.contains("perf.int000"));
                return;
            }
            Assert.fail();
        } catch (IOException x) {
            throw new IllegalArgumentException("Tests failed on file handling: ", x);
        }
    }

    private void reader11(File f) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)))){
            String line = reader.readLine();
            int counter = 0;
            String time = null;
            while (line != null) {
                String[] args = line.split(",");
                Assert.assertEquals(4, args.length);
                args[0] = args[0].replace("\"", "");
                args[1] = args[1].replace("\"", "");
                args[2] = args[2].replace("\"", "");
                args[3] = args[3].replace("\"", "");
                Assert.assertEquals(args[1], String.valueOf(10 + counter));
                SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                try {
                    date.parse(args[0]);
                } catch (ParseException e) {
                    Assert.fail("Date format");
                }
                String time2 = args[0].split(" ")[1].split(":")[2];
                if(time != null){
                    if ((Integer.parseInt(time) + 1) == 60){
                        time = "-1";
                    }
                    System.out.println(String.valueOf(Integer.parseInt(time) + 1).equals(time2));
                    System.out.println(time);
                    System.out.println(time2);
                    if ((Integer.parseInt(time) + 1) != (Integer.parseInt(time2))){
                        Assert.fail();
                    }
                }
                time = time2;
                Assert.assertEquals(args[2], "TEST_APP");
                Assert.assertEquals(args[3], "perf.int000");
                line = reader.readLine();
                counter++;
            }
            Assert.assertEquals(7, counter);
        } catch (IOException x) {
            throw new IllegalArgumentException("Tests failed on file handling: ", x);
        }
    }

    private void firstLineChecker(File f) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)))){
            String line = reader.readLine();
            if (line != null) {
                Assert.assertFalse(line.contains("perf.int001"));
                return;
            }
            Assert.fail();
        } catch (IOException x) {
            throw new IllegalArgumentException("Tests failed on file handling: ", x);
        }
    }

    private void reader2(File f) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)))){
            throw new IllegalArgumentException("Tests failed on file handling: ");
        } catch (IOException x) {
            Assert.assertTrue(true);
        }
    }

    @Before
    public void setUp() {
        MeasurementConfigurationService measurementConfigurationService = new MeasurementConfigurationServiceImpl();
        MeasurementConfigurationDaoImpl dao = new MeasurementConfigurationDaoImpl();
        dao.setFilepath("src/test/resources/test_measurement_config.json");
        measurementConfigurationService.setMeasurementConfigurationDao(dao);
        this.measurementExportService = new MeasurementExportServiceImpl();
        this.measurementExportService.setMeasurementConfigurationService(measurementConfigurationService);
        this.measurementExportService.setTimer(1000); // for test efficiency
        System.out.println("These tests might take around 30 seconds, so sorry in advance");
    }

    public void clear() {
        File f = new File(String.valueOf(Paths.get(this.folderPath,"TEST_APP-perf.int000.csv")));
        if(!f.delete()){
            Assert.fail();
        }
    }

    @Test
    public void testExportServiceDefault() {
        measurementExportService.exportMeasurements(new File("."), new ArrayList<>());
    }

    @Test
    public void testIfFileExports() {
        List<String> lst = new ArrayList<>();
        lst.add("perf.int000");
        this.measurementExportService.exportMeasurements(new File(this.folderPath), lst);
        File f = new File(String.valueOf(Paths.get(this.folderPath,"TEST_APP-perf.int000.csv")));
        reader1(f);
        clear();
    }

    @Test
    public void testWhatFileContainsComplex() {
        List<String> lst = new ArrayList<>();
        lst.add("perf.int000");
        this.measurementExportService.exportMeasurements(new File(this.folderPath), lst);
        File f = new File(String.valueOf(Paths.get(this.folderPath,"TEST_APP-perf.int000.csv")));
        reader11(f);
        clear();
    }

    @Test
    public void testIfFileExportsOnlyOneID() {
        List<String> lst = new ArrayList<>();
        lst.add("perf.int000");
        this.measurementExportService.exportMeasurements(new File(this.folderPath), lst);
        File f = new File(String.valueOf(Paths.get(this.folderPath,"TEST_APP-perf.int000.csv")));
        firstLineChecker(f);
        clear();
    }

    @Test
    public void testNoFile() {
        this.measurementExportService.exportMeasurements(new File(this.folderPath), new ArrayList<>());
        File f = new File(String.valueOf(Paths.get(this.folderPath,"TEST_APP-perf.int000.csv")));
        reader2(f);
    }

    @Test
    public void testNoFileRandom() {
        List<String> lst = new ArrayList<>();
        lst.add("whatever thingy");
        this.measurementExportService.exportMeasurements(new File(this.folderPath), lst);
        File f = new File(String.valueOf(Paths.get(this.folderPath,"TEST_APP-perf.int000.csv")));
        reader2(f);
    }

    @Test
    public void testNoFileID() {
        List<String> lst = new ArrayList<>();
        lst.add("perf.int001");
        this.measurementExportService.exportMeasurements(new File(this.folderPath), lst);
        File f = new File(String.valueOf(Paths.get(this.folderPath,"TEST_APP-perf.int000.csv")));
        reader2(f);
    }

    @Test
    public void timerCheck() {
        List<String> lst = new ArrayList<>();
        lst.add("perf.int000");
        long start = System.currentTimeMillis();
        this.measurementExportService.exportMeasurements(new File(this.folderPath), lst);
        long end = System.currentTimeMillis();
        // 6999 should be the highest cap according to test config
        if ((end - start) > 6999 || (end - start) < 6000) {
            Assert.fail();
        }
        clear();
    }
}