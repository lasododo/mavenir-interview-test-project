/**
 * Java candidate interview test.
 * Copyright (c) 2010-2018 Mavenir
 * http://www.mavenir.com
 * All Rights Reserved.
 */
package com.mavenir.interview.measurement.dao.impl;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import com.mavenir.interview.measurement.api.dto.MeasurementDefinionDto;
import com.mavenir.interview.measurement.dao.MeasurementConfigurationDao;

/**
 * Test of {@code MeasurementConfigurationDaoImpl} class.
 */
public class MeasurementConfigurationDaoImplTest {

    private MeasurementConfigurationDao measurementConfigurationDao;
    private List<MeasurementDefinionDto> allMD;
    private MeasurementConfigurationDaoImpl impl;

    @Before
    public void setUp() {
        this.measurementConfigurationDao = new MeasurementConfigurationDaoImpl();
        this.allMD = measurementConfigurationDao.getMeasurementDefinitions();
        this.impl = new MeasurementConfigurationDaoImpl();
    }

    @Test
    public void testConfigurationDao() {
        final List<MeasurementDefinionDto> allMeasurementDefinitions =
                measurementConfigurationDao.getMeasurementDefinitions();

        assertThat("Result", allMeasurementDefinitions, notNullValue());
    }

    @Test
    public void checkValidToStringAll() {
        Object[] array = allMD.toArray();
        for (Object obj : array){
            String result = obj.toString();
            Assert.assertTrue(result.contains("App name:"));
            Assert.assertTrue(result.contains("Measurement ID:"));
            Assert.assertTrue(result.contains("Class ID:"));
            Assert.assertTrue(result.contains("Instance ID:"));
            Assert.assertTrue(result.contains("Initial Value:"));
            Assert.assertTrue(result.contains("Increment:"));
            Assert.assertTrue(result.contains("Interval:"));
        }
    }

    @Test
    public void checkIfToStringContainsData() {
        String filepath = "src/main/resources/measurement_config.json";

        Gson gson = new Gson();
        try (JsonReader reader = new JsonReader(new FileReader(filepath))) {
            MeasurementDefinionDto[] data = gson.fromJson(reader, MeasurementDefinionDto[].class);

            Assert.assertTrue(data[0].toString().contains("App name:"));
            Assert.assertTrue(data[0].toString().contains("Measurement ID:"));
            Assert.assertTrue(data[0].toString().contains("Class ID:"));
            Assert.assertTrue(data[0].toString().contains("Instance ID:"));
            Assert.assertTrue(data[0].toString().contains("Initial Value:"));
            Assert.assertTrue(data[0].toString().contains("Increment:"));
            Assert.assertTrue(data[0].toString().contains("Interval:"));

            assertThat(data[0].getAppName(), notNullValue());
            assertThat(data[0].getClassId(), notNullValue());
            assertThat(data[0].getIncrement(), notNullValue());
            assertThat(data[0].getInstanceId(), notNullValue());
            assertThat(data[0].getInitialValue(), notNullValue());
            assertThat(data[0].getMeasurementId(), notNullValue());
            assertThat(data[0].getInterval(), notNullValue());

        } catch (IOException x) {
            x.printStackTrace();
            throw new IllegalArgumentException("Tests could not be executed correctly");
        }
    }

    @Test
    public void checkIfTestFileIsCorrect() {
        String filepath = "src/test/resources/test_measurement_config.json";

        this.impl.setFilepath(filepath);

        List<MeasurementDefinionDto> lst = this.impl.getMeasurementDefinitions();
        MeasurementDefinionDto[] data = lst.toArray(new MeasurementDefinionDto[0]);
        Gson gson = new Gson();

        Assert.assertEquals(1, lst.size());

        try (JsonReader reader = new JsonReader(new FileReader(filepath))) {
            MeasurementDefinionDto[] data2 = gson.fromJson(reader, MeasurementDefinionDto[].class);

            Assert.assertArrayEquals(data, data2);

        } catch (IOException x) {
            x.printStackTrace();
            throw new IllegalArgumentException("Tests could not be executed correctly");
        }

    }
}