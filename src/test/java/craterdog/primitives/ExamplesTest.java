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
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;


/**
 * This class defines and validates the example code shown in the README.md file.
 *
 * @author Derk Norton
 */
public class ExamplesTest {

    static private final XLogger log = XLoggerFactory.getXLogger(ExamplesTest.class);


    /**
     * Log a message at the beginning of the tests.
     */
    @BeforeClass
    public static void setUpClass() {
        log.info("Running Tag Unit Tests...\n");
    }

    /**
     * Log a message at the end of the tests.
     */
    @AfterClass
    public static void tearDownClass() {
        log.info("Completed Tag Unit Tests.\n");
    }

    /**
     * This method tests the angle class examples.
     */
    @Test
    public void testAngleExamples() {
        log.info("Beginning testAngleExamples()...");

        // Create some angles
        Angle pi = Angle.PI;
        Angle piOver2 = new Angle(Math.PI / 2.0d);
        Angle piOver3 = Angle.quotient(pi, 3.0d);
        Angle piOver4 = Angle.quotient(piOver2, 2.0d);
        Angle piOver6 = Angle.product(piOver3, 0.5d);

        // Do some angle calculations
        log.info("The value of pi is: {}", pi);
        log.info("The value of pi/2 is: {}", piOver2);
        log.info("The value of pi/3 is: {}", piOver3);
        log.info("The value of pi/4 is: {}", piOver4);
        log.info("The value of pi/6 is: {}", piOver6);
        log.info("The negative of pi/6 is: {}", Angle.negate(piOver6));
        log.info("The inversion of pi/3 is: {}", Angle.invert(piOver3));
        log.info("The sum of pi/6 and pi/3 is: {}", Angle.sum(piOver6, piOver3));
        log.info("The difference of pi/3 and pi/2 is: {}", Angle.difference(piOver3, piOver2));
        log.info("The sine of pi/6 is: {}", Angle.sine(piOver6));
        log.info("The cosine of pi/3 is: {}", Angle.cosine(piOver3));
        log.info("The tangent of pi/2 is: {}", Angle.tangent(piOver2));
        log.info("The arctangent of 1.0 is: {}", Angle.arctangent(1.0));

        log.info("Completed testAngleExamples().\n");
    }

    /**
     * This method tests the probability class examples.
     */
    @Test
    public void testProbabilityExamples() {
        log.info("Beginning testProbabilityExamples()...");

        // Create some probabilities
        Probability always = new Probability(1.0d);
        Probability threeQuarters = new Probability(0.75d);
        Probability oneHalf = new Probability(0.5d);
        Probability oneThird = new Probability(1.0d / 3.0d);
        Probability oneQuarter = new Probability(0.75d);
        Probability never = new Probability(0.0d);

        // Do some angle calculations
        log.info("The value of always is: {}", always);
        log.info("The value of threeQuarters is: {}", threeQuarters);
        log.info("The value of oneHalf is: {}", oneHalf);
        log.info("The value of oneThird is: {}", oneThird);
        log.info("The value of oneQuarter is: {}", oneQuarter);
        log.info("The value of never is: {}", never);
        log.info("The inversion of never is: {}", Probability.not(never));
        log.info("The intersection of threeQuarters and oneThird is: {}", Probability.and(threeQuarters, oneThird));
        log.info("The union of oneHalf and oneHalf is: {}", Probability.or(oneHalf, oneHalf));
        log.info("The difference (sans) of oneHalf and oneHalf is: {}", Probability.sans(oneHalf, oneHalf));
        log.info("The exclusive union (xor) of oneHalf and oneHalf is: {}", Probability.xor(oneHalf, oneHalf));
        log.info("The results of a random coin toss are: {}", Probability.coinToss(oneHalf));

        log.info("Completed testProbabilityExamples().\n");
    }

    /**
     * This method tests the tag class examples.
     */
    @Test
    public void testTagExamples() {
        log.info("Beginning testTagExamples()...");

        // Create some tags
        Tag small = new Tag(2);
        Tag medium = new Tag(8);
        Tag large = new Tag();  // defaults to 20 bytes
        Tag huge = new Tag(128);

        // Display the tags
        log.info("The default format of a random 2 byte tag is: {}", small);
        log.info("The default format of a random 8 byte tag is: {}", medium);
        log.info("The default format of a random 20 byte tag is: {}", large);
        log.info("The default format of a random 128 byte tag is: {}", huge);

        log.info("Completed testTagExamples().\n");
    }

}
