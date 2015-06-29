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
public class ProbabilityTest {

    static private final XLogger logger = XLoggerFactory.getXLogger(ProbabilityTest.class);


    /**
     * Log a message at the beginning of the tests.
     */
    @BeforeClass
    static public void setUpClass() {
        logger.info("Running Probability Unit Tests...\n");
    }


    /**
     * Log a message at the end of the tests.
     */
    @AfterClass
    static public void tearDownClass() {
        logger.info("Completed Probability Unit Tests.\n");
    }


    /**
     * This method exercises the constructors and logical operations on this class.
     */
    @Test
    public void testConstructorsAndLogicalOperations() {
        logger.info("Beginning testConstructorsAndLogicalOperations()...");

        Probability lowest = new Probability(0.0);
        logger.info("  Lowest: " + lowest.toDouble());
        Probability quarter = new Probability("0.25");
        logger.info("  Quarter: " + quarter.toDouble());
        Probability third = new Probability(1.0d/3.0d);
        logger.info("  Third: " + third.toDouble());
        Probability middle = new Probability("0.5");
        logger.info("  Middle: " + middle.toDouble());
        Probability threeQuarter = new Probability(0.75);
        logger.info("  Three Quarter: " + threeQuarter.toDouble());
        Probability highest = new Probability(1.0);
        logger.info("  Highest: " + highest.toDouble());
        assertEquals(quarter, Probability.not(threeQuarter));
        assertEquals(quarter, Probability.and(threeQuarter, third));
        assertEquals(threeQuarter, Probability.or(middle, middle));
        assertEquals(middle, Probability.xor(middle, middle));
        assertEquals(quarter, Probability.sans(middle, middle));
        assertFalse(Probability.coinToss(lowest));
        assertTrue(Probability.coinToss(highest));

        logger.info("Completed testConstructorsAndLogicalOperations().\n");
    }

}
