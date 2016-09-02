/************************************************************************
 * Copyright (c) Crater Dog Technologies(TM).  All Rights Reserved.     *
 ************************************************************************
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.        *
 *                                                                      *
 * This code is free software; you can redistribute it and/or modify it *
 * under the terms of The MIT License (MIT), as published by the Open   *
 * Source Initiative. (See http://opensource.org/licenses/MIT)          *
 ************************************************************************/
package craterdog.primitives;

import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;


/**
 * This class performs unit tests on the <code>Probability</code> class.
 *
 * @author Derk Norton
 */
public class VersionStringTest {

    static private final XLogger logger = XLoggerFactory.getXLogger(VersionStringTest.class);


    /**
     * Log a message at the beginning of the tests.
     */
    @BeforeClass
    static public void setUpClass() {
        logger.info("Running Version Unit Tests...\n");
    }


    /**
     * Log a message at the end of the tests.
     */
    @AfterClass
    static public void tearDownClass() {
        logger.info("Completed Version Unit Tests.\n");
    }


    /**
     * This method exercises the constructors and logical operations on this class.
     */
    @Test
    public void testConstructorsAndFunctions() {
        logger.info("Beginning testConstructorsAndFunctions()...");

        VersionString major = new VersionString("3");
        assertEquals("4", VersionString.getNextVersion(major).toString());
        assertEquals("3.1", VersionString.getNewVersion(major, 2).toString());

        VersionString minor = new VersionString(1, 2);
        assertEquals("1.3", VersionString.getNextVersion(minor).toString());
        assertEquals("1.2.1", VersionString.getNewVersion(minor, 3).toString());

        VersionString middle = new VersionString("1.2.3");
        assertEquals("1.2.4", VersionString.getNextVersion(middle).toString());
        assertEquals("1.3", VersionString.getNewVersion(middle, 2).toString());
        int count = 1;
        for (int version : middle) {
            assertEquals(count++, version);
        }

        try {
            VersionString negative = new VersionString(-1);
            fail("Should have thrown a NumberFormatException.");
        } catch (NumberFormatException e) {
            // expected
        }

        try {
            VersionString zero = new VersionString("2.0");
            fail("Should have thrown a NumberFormatException.");
        } catch (NumberFormatException e) {
            // expected
        }

        try {
            VersionString empty = new VersionString();
            fail("Should have thrown a NumberFormatException.");
        } catch (NumberFormatException e) {
            // expected
        }

        logger.info("Completed testConstructorsAndFunctions().\n");
    }

}
