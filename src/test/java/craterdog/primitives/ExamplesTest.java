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

import craterdog.utils.RandomUtils;
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

    static private final XLogger logger = XLoggerFactory.getXLogger(ExamplesTest.class);


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
     * This method tests the angle class examples.
     */
    @Test
    public void testAngleExamples() {
        logger.info("Beginning testAngleExamples()...");

        // Create some angles
        Angle pi = Angle.PI;
        Angle piOver2 = new Angle(Math.PI / 2.0d);
        Angle piOver3 = Angle.quotient(pi, 3.0d);
        Angle piOver4 = Angle.quotient(piOver2, 2.0d);
        Angle piOver6 = Angle.product(piOver3, 0.5d);

        // Do some angle calculations
        logger.info("  The value of pi is: {}", pi);
        logger.info("  The value of pi/2 is: {}", piOver2);
        logger.info("  The value of pi/3 is: {}", piOver3);
        logger.info("  The value of pi/4 is: {}", piOver4);
        logger.info("  The value of pi/6 is: {}", piOver6);
        logger.info("  The negative of pi/6 is: {}", Angle.negate(piOver6));
        logger.info("  The inversion of pi/3 is: {}", Angle.invert(piOver3));
        logger.info("  The sum of pi/6 and pi/3 is: {}", Angle.sum(piOver6, piOver3));
        logger.info("  The difference of pi/3 and pi/2 is: {}", Angle.difference(piOver3, piOver2));
        logger.info("  The sine of pi/6 is: {}", Angle.sine(piOver6));
        logger.info("  The cosine of pi/3 is: {}", Angle.cosine(piOver3));
        logger.info("  The tangent of pi/2 is: {}", Angle.tangent(piOver2));
        logger.info("  The arctangent of 1.0 is: {}", Angle.arctangent(1.0));

        logger.info("Completed testAngleExamples().\n");
    }

    /**
     * This method tests the probability class examples.
     */
    @Test
    public void testProbabilityExamples() {
        logger.info("Beginning testProbabilityExamples()...");

        // Create some probabilities
        Probability always = new Probability(1.0d);
        Probability threeQuarters = new Probability(0.75d);
        Probability oneHalf = new Probability(0.5d);
        Probability oneThird = new Probability(1.0d / 3.0d);
        Probability oneQuarter = new Probability(0.75d);
        Probability never = new Probability(0.0d);

        // Do some angle calculations
        logger.info("  The value of always is: {}", always);
        logger.info("  The value of threeQuarters is: {}", threeQuarters);
        logger.info("  The value of oneHalf is: {}", oneHalf);
        logger.info("  The value of oneThird is: {}", oneThird);
        logger.info("  The value of oneQuarter is: {}", oneQuarter);
        logger.info("  The value of never is: {}", never);
        logger.info("  The inversion of never is: {}", Probability.not(never));
        logger.info("  The intersection of threeQuarters and oneThird is: {}", Probability.and(threeQuarters, oneThird));
        logger.info("  The union of oneHalf and oneHalf is: {}", Probability.or(oneHalf, oneHalf));
        logger.info("  The difference (sans) of oneHalf and oneHalf is: {}", Probability.sans(oneHalf, oneHalf));
        logger.info("  The exclusive union (xor) of oneHalf and oneHalf is: {}", Probability.xor(oneHalf, oneHalf));
        logger.info("  The results of a random coin toss are: {}", Probability.coinToss(oneHalf));

        logger.info("Completed testProbabilityExamples().\n");
    }

    /**
     * This method tests the tag class examples.
     */
    @Test
    public void testTagExamples() {
        logger.info("Beginning testTagExamples()...");

        // Create some tags
        Tag small = new Tag(2);
        Tag medium = new Tag(8);
        Tag large = new Tag();  // defaults to 20 bytes
        Tag huge = new Tag(128);

        // Display the tags
        logger.info("  Here is a random 2 byte tag is: {}", small);
        logger.info("  Here is a random 8 byte tag is: {}", medium);
        logger.info("  Here is a random 20 byte tag is: {}", large);
        logger.info("  Here is a random 128 byte tag is: {}", huge);

        logger.info("Completed testTagExamples().\n");
    }

    /**
     * This method tests the text string class examples.
     */
    @Test
    public void testTextStringExamples() {
        logger.info("Beginning testTextStringExamples()...");

        // Create some text strings
        TextString emptyText = new TextString();  // empty text string
        assert emptyText.isEmpty();
        TextString shortText = new TextString("abcde");
        assert shortText.getNumberOfElements() == 5;

        // Iterate through the characters
        for (Character c : shortText) {
            logger.info("  Character: '{}'", c);
        }

        logger.info("Completed testTextStringExamples().\n");
    }

    /**
     * This method tests the binary string class examples.
     */
    @Test
    public void testBinaryStringExamples() {
        logger.info("Beginning testBinaryStringExamples()...");

        // Create a binary string
        BinaryString randomBinary = new BinaryString(RandomUtils.generateRandomBytes(100));
        assert randomBinary.getNumberOfElements() == 100;

        // Format the bytes using various bases
        logger.info("  The binary string in base 2 is: {}\n", randomBinary.toString(2, "  "));
        logger.info("  The binary string in base 16 is: {}\n", randomBinary.toString(16, "  "));
        logger.info("  The binary string in base 32 is: {}\n", randomBinary.toString(32, "  "));
        logger.info("  The binary string in base 64 is: {}\n", randomBinary.toString(64, "  "));

        logger.info("Completed testBinaryStringExamples().\n");
    }

}
