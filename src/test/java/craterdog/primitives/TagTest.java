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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;


/**
 * This class performs unit tests on the <code>Tag</code> class.
 *
 * @author Derk Norton
 */
public class TagTest {

    static private final XLogger logger = XLoggerFactory.getXLogger(TagTest.class);


    /**
     * Log a message at the beginning of the tests.
     */
    @BeforeClass
    public static void setUpClass() {
        logger.info("Running Tag Unit Tests...\n");
    }

    /**
     * Log a message at the end of the tests.
     */
    @AfterClass
    public static void tearDownClass() {
        logger.info("Completed Tag Unit Tests.\n");
    }

    /**
     * This method tests round-trip conversions of different tags.
     */
    @Test
    public void testRoundTrip() {
        logger.info("Beginning testRoundTrip()...");

        for (int i = 0; i <= 32; i++) {
            Tag tag1 = new Tag(i);
            Tag tag2 = new Tag(tag1.toBytes());
            Tag tag3 = new Tag(tag2.toString());
            Tag tag4 = new Tag(i);
            logger.info("  Tag size " + i + ": " + tag1.toString());
            assertTrue(tag2.equals(tag1));
            assertTrue(tag3.equals(tag1));
            assertTrue(tag3.equals(tag2));
            int compareBytes = tag1.compareTo(tag4);
            int compareStrings = Integer.signum(tag1.toString().compareTo(tag4.toString()));
            assertEquals(compareStrings, compareBytes);
        }
        for (int i = 0; i < 100; i++) {
            logger.info("  Tag: " + new Tag().toString());
        }

        logger.info("Completed testRoundTrip().");
    }

}
