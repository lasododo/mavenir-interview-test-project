/**
 * Java candidate interview test.
 * Copyright (c) 2010-2018 Mavenir
 * http://www.mavenir.com
 * All Rights Reserved.
 */
package com.mavenir.interview.measurement.dao.impl;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.nio.file.Path;

// import org.json.*;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.mavenir.interview.measurement.api.dto.MeasurementDefinionDto;
import com.mavenir.interview.measurement.dao.MeasurementConfigurationDao;

/**
 * Implementation of {@code MeasurementConfigurationDao}.
 */
public class MeasurementConfigurationDaoImpl implements MeasurementConfigurationDao {

    // will help you fix path if its needed -> System.out.println(System.getProperty("user.dir"));
    private String filepath = "src/main/resources/measurement_config.json";

    public void setFilepath(String path) {
        this.filepath = path;
    }

    @Override
    public List<MeasurementDefinionDto> getMeasurementDefinitions() {
        Gson gson = new Gson();
        try (JsonReader reader = new JsonReader(new FileReader(this.filepath))) {
            MeasurementDefinionDto[] data = gson.fromJson(reader, MeasurementDefinionDto[].class);
            return new ArrayList<>(Arrays.asList(data));
        } catch (IOException x) {
            x.printStackTrace();
            throw new IllegalArgumentException("Wrong file");
        }
    }
}
