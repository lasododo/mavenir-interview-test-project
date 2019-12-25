/**
 * Java candidate interview test.
 * Copyright (c) 2010-2018 Mavenir
 * http://www.mavenir.com
 * All Rights Reserved.
 */
package com.mavenir.interview.measurement.api.dto;

import java.util.Objects;

/**
 * Measurement definition DTO (Data Transfer Object).
 */
public class MeasurementDefinionDto {

    private String appName;
    private String measurementId;
    private String classId;
    private String instanceId;
    private int initialValue;
    private int increment;
    private int interval;

    public MeasurementDefinionDto(String name, String measID, String clsID, String instID,
                                  int init, int increment, int interval) {
        if (nullChecker(name, measID, clsID, instID)) {
            throw new IllegalArgumentException("one of the string arguments is null ");
        }
        // can be replaced with setters
        this.appName = name;
        this.measurementId = measID;
        this.classId = clsID;
        this.instanceId = instID;
        this.initialValue = init;
        this.increment = increment;
        this.interval = interval;
    }

    private boolean nullCheck(String str){
        return str == null;
    }

    private boolean nullChecker(String name, String measID, String clsID, String instID) {
        return nullCheck(name) || nullCheck(measID) || nullCheck(clsID) || nullCheck(instID);
    }

    public String getAppName() {
        return appName;
    }

    public String getMeasurementId() {
        return measurementId;
    }

    public String getClassId() {
        return classId;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public int getInitialValue() {
        return initialValue;
    }

    public void multiplyInit(int value){
        this.initialValue += value;
    }

    public int getIncrement() {
        return increment;
    }

    public int getInterval() {
        return interval;
    }

    @Override
    public int hashCode() {
        return Objects.hash(appName, measurementId, classId, instanceId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MeasurementDefinionDto that = (MeasurementDefinionDto) o;
        return Objects.equals(appName, that.appName) &&
                Objects.equals(measurementId, that.measurementId) &&
                Objects.equals(classId, that.classId) &&
                Objects.equals(instanceId, that.instanceId);
    }

    @Override
    public String toString() {

        return  "App name: " + getAppName() + // could have used this.appName, but its the same
                " , Measurement ID: " + getMeasurementId() +
                " , Class ID: " + getClassId() +
                " , Instance ID: " + getInstanceId() +
                " , Initial Value: " + getInitialValue() +
                " , Increment: " + getIncrement() +
                " , Interval: " + getInterval();
    }
}
