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

    private final String appName;
    private final String measurementId;
    private final String classId;
    private final String instanceId;
    // used it as value that is being increased by increment in interval
    private int initialValue;
    private final int increment;
    private final int interval;

    /**
     * constructor
     * @param name -> App name
     * @param measID -> Measurement ID
     * @param clsID -> Class ID
     * @param instID -> Instance ID
     * @param init -> Initial Value
     * @param increment -> increment value
     * @param interval -> interval value ( has to end with 0, or else will be rounded up )
     */
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

    /**
     * checks if string is null
     * @param str -> string
     * @return -> True if null , False if its not null
     */
    private boolean nullCheck(String str){
        return str == null;
    }

    /**
     * nullCheck of all strings
     * @param name -> App name
     * @param measID -> Measurement ID
     * @param clsID -> Class ID
     * @param instID -> Instance ID
     * @return -> True if any of those are null , otherwise False
     */
    private boolean nullChecker(String name, String measID, String clsID, String instID) {
        return nullCheck(name) || nullCheck(measID) || nullCheck(clsID) || nullCheck(instID);
    }

    /**
     * AppName getter
     * @return -> app Name (String)
     */
    public String getAppName() {
        return appName;
    }

    /**
     * measurementId getter
     * @return -> measurementId (String)
     */
    public String getMeasurementId() {
        return measurementId;
    }

    /**
     * classId getter
     * @return -> classId (String)
     */
    public String getClassId() {
        return classId;
    }

    /**
     * instanceId getter
     * @return -> instanceId (String)
     */
    public String getInstanceId() {
        return instanceId;
    }

    /**
     * initialValue getter
     * @return -> initialValue (int)
     */
    public int getInitialValue() {
        return initialValue;
    }

    /**
     * initialValue increase function
     * @param value -> number that represents ho much do we want to change the initialValue
     * @return -> initialValue + value (int)
     */
    public void multiplyInit(int value){
        this.initialValue += value;
    }

    /**
     * increment getter
     * @return -> increment (int)
     */
    public int getIncrement() {
        return increment;
    }

    /**
     * interval getter
     * @return -> interval (int)
     */
    public int getInterval() {
        return interval;
    }

    @Override
    public int hashCode() {
        // measurement ID's must be different, so it could be just those, but just in case ..
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
        // measurement ID's must be different, so it could be just those, but just in case ..
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
