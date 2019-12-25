package com.mavenir.interview.measurement.app;

import org.junit.Assert;
import org.junit.Test;

public class MainTest {

    private final String filename = "src/test/resources/export";

    @Test
    public void testLessThan2ArgumentIsGiven() {
        String[] arguments = new String[1];
        arguments[0] = this.filename;

        try {
            Main.main(arguments);
            Assert.fail();
        } catch (IllegalArgumentException x){
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testMoreThan2ArgumentAreGiven() {
        String argument =  "perf.int000";

        String[] arguments = new String[3];
        arguments[0] = this.filename;
        arguments[1] = argument;

        try {
            Main.main(arguments);
            Assert.fail();
        } catch (IllegalArgumentException x){
            Assert.assertTrue(true);
        }
    }

    @Test
    public void test2GivenArguments() {
        String argument =  "perf.int000";

        String[] arguments = new String[2];
        arguments[0] = this.filename;
        arguments[1] = argument;

        try {
            Main.main(arguments);
            Assert.assertTrue(true);
        } catch (IllegalArgumentException x){
            Assert.fail();
        }
    }

    @Test
    public void testWrongFile() {
        String argument =  "perf.int000";

        String[] arguments = new String[2];
        arguments[0] = "random_wtf_file.bla";
        arguments[1] = argument;

        try {
            Main.main(arguments);
            Assert.fail();
        } catch (IllegalArgumentException x){
            Assert.assertTrue(true);
        }
    }
}
