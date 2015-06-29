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
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;


/**
 * This class performs unit tests on the <code>Angle</code> class.
 *
 * @author Derk Norton
 */
public class AngleTest {

    static private final XLogger logger = XLoggerFactory.getXLogger(AngleTest.class);


    /**
     * Log a message at the beginning of the tests.
     */
    @BeforeClass
    static public void setUpClass() {
        logger.info("Running Angle Unit Tests...\n");
    }


    /**
     * Log a message at the end of the tests.
     */
    @AfterClass
    static public void tearDownClass() {
        logger.info("Completed Angle Unit Tests.\n");
    }


    /**
     * This method performs various round-trip conversions on angles.
     */
    @Test
    public void testRoundTrip() {
        logger.info("Beginning testRoundTrip()...");

        double piOver4 = Math.PI / 4.0d;
        for (double a = -9.0d * piOver4; a < 10.0d * piOver4; a += piOver4) {
            Angle angle = new Angle(a);
            logger.info("  Angle: " + a);
            logger.info("  Normalized: " + angle.toString());

            Angle inverted = Angle.invert(new Angle(angle.toString()));
            logger.info("  Inverted: " + inverted.toString());
            Angle reinverted = Angle.invert(inverted);
            logger.info("  Reinverted: " + reinverted.toString());
            assertEquals(angle, reinverted);

            Angle negated = Angle.negate(new Angle(angle.toString()));
            logger.info("  Negated: " + negated.toString());
            Angle renegated = Angle.negate(negated);
            logger.info("  Renegated: " + renegated.toString());
            assertEquals(angle, renegated);

            Angle doubled = Angle.sum(angle, angle);
            logger.info("  Doubled: " + doubled.toString());
            Angle halved = Angle.difference(doubled, angle);
            logger.info("  Halved: " + halved.toString());
            assertEquals(angle, halved);

            halved = Angle.quotient(angle, 2.0d);
            logger.info("  Divided by 2: " + halved.toString());
            doubled = Angle.product(halved, 2.0d);
            logger.info("  Multiplied by 2: " + doubled.toString());
            assertEquals(angle, doubled);

            double sine = Angle.sine(angle);
            logger.info("  Sine: " + sine);
            Angle arcsine = Angle.arcsine(sine);
            logger.info("  Arcsine: " + arcsine);
            assertEquals(Angle.sine(arcsine), sine, Angle.EPSILON);

            double cosine = Angle.cosine(angle);
            logger.info("  Cosine: " + Double.toString(cosine));
            Angle arccosine = Angle.arccosine(cosine);
            logger.info("  Arccosine: " + arccosine);
            assertEquals(Angle.cosine(arccosine), cosine, Angle.EPSILON);

            double tangent = Angle.tangent(angle);
            logger.info("  Tangent: " + Double.toString(tangent));
            Angle arctangent = Angle.arctangent(tangent);
            logger.info("  Arctangent: " + arctangent);
            assertEquals(Angle.tangent(arctangent), tangent, Angle.EPSILON);

        }

        logger.info("Completed testRoundTrip().\n");
    }

}
