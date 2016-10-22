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

import java.util.Arrays;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;


/**
 * This class performs unit tests on the <code>BinaryString</code> class.
 *
 * @author Derk Norton
 */
public class BinaryStringTest {

    static private final XLogger logger = XLoggerFactory.getXLogger(BinaryStringTest.class);


    /**
     * Log a message at the beginning of the tests.
     */
    @BeforeClass
    public static void setUpClass() {
        logger.info("Running BinaryString Unit Tests...\n");
    }


    /**
     * Log a message at the end of the tests.
     */
    @AfterClass
    public static void tearDownClass() {
        logger.info("Completed BinaryString Unit Tests.\n");
    }


    /**
     * Test the constructors that decode strings and compare them to the output of the
     * corresponding toString method.
     */
    @Test
    public void testConstructorsAndToString() {
        logger.info("Beginning testConstructorsAndToString()...");
        String base02String = "0110100100010101";
        BinaryString text = new BinaryString(base02String, 2);
        assertEquals("The constructor and equals methods are incompatible.", base02String, text.toString(2));
        String base16String = "39E1BB74";
        text = new BinaryString(base16String, 16);
        assertEquals("The constructor and equals methods are incompatible.", base16String, text.toString(16));
        String base32String = "7ZQ8G6ZJZDV8T";
        text = new BinaryString(base32String, 32);
        assertEquals("The constructor and equals methods are incompatible.", base32String, text.toString(32));
        String base64String = "" +
                "    aR0vNePXNOssIpkx8mTUZSN3dPF88VKzRzjUtXeMqkoCtVSgJjTOHpxbyfMMROAEBrTviql0hREkYUau\n" +
                "    YPcTCq+YM9Z1aW7LlyQIqeb8Wk/RO1MrBeZNotcOabU2Ok2JKa8O4sqL3Xi091380gx6GeZHFVsBZK/R\n" +
                "    4DsMkTWPUh8SvHMas9xvsldjl665qGBE7zgyKZ9OvaV4L/h3K4cNd0qaiFYEGhAxkNImFe9fpYDlgK9j\n" +
                "    mTMUem8yRMoCb8zr3ZKWMGRhrrpCpp1+1QSPCUGoEOjusCNJnl/bEtE23ymEGnUtdo5aRzjnLyU3oj4z\n" +
                "    yN9J9mGnr9+PgPg0mR/KxZbnbdfGZE4tJ6eqeTB5Vmui4y4xTddkdclT0zvK6jLH0AH9+yeRSM6P7fgw\n" +
                "    9ov4w2t6y6bL8j10N2IUucNGkrZv0UI0FwQOhcgu5SpY42p8KPBb28z7qjYLfIGs6egtbJwGigUNNMJZ";
        text = new BinaryString(base64String, 64);
        assertEquals("The constructor and equals methods are incompatible.", base64String, text.toString("    "));

        base64String = "UQ==";
        text = new BinaryString(base64String);
        assertEquals("The constructor and equals methods are incompatible.", base64String, text.toString());

        logger.info("Completed testConstructorsAndToString().");
    }


    /**
     * Test the equals and compareTo methods.
     */
    @Test
    public void testEqualsAndCompareTo() {
        logger.info("Beginning testEqualsAndCompareTo()...");
        BinaryString nullString = null;
        BinaryString emptyString = new BinaryString(new byte[0]);
        BinaryString lowerString = new BinaryString("7ZQ8G6ZJZDV8T", 32);
        BinaryString higherString = new BinaryString("7ZQ8M6ZJZDV8T", 32);
        assert !emptyString.equals(nullString);
        assert emptyString.compareTo(nullString) > 0;
        assert emptyString.equals(emptyString);
        assert emptyString.compareTo(emptyString) == 0;
        assert !lowerString.equals(higherString);
        assert lowerString.compareTo(emptyString) > 0;
        assert lowerString.compareTo(higherString) < 0;
        logger.info("Completed testEqualsAndCompareTo().");
    }


    /**
     * Test the hashCode method.
     */
    @Test
    public void testHashCode() {
        logger.info("Beginning testHashCode()...");
        BinaryString text = new BinaryString("7ZQ8G6ZJZDV8T", 32);
        assertEquals("The hashCode methods are not consistent.", Arrays.hashCode(text.toBytes()), text.hashCode());
        logger.info("Completed testHashCode().");
    }


    /**
     * Test the isEmpty, length and getNumberOfElements methods.
     */
    @Test
    public void testIsEmptyAndLengthAndGetNumberOfElements() {
        logger.info("Beginning testIsEmptyAndLengthAndGetNumberOfElements()...");
        BinaryString text = new BinaryString("", 2);
        assertTrue("The isEmpty methods are not consistent.", text.isEmpty());
        assertEquals("The getNumberOfElements methods are not consistent.", text.toBytes().length, text.getSize());

        text = new BinaryString("7ZQ8G6ZJZDV8T", 32);
        assertFalse("The isEmpty methods are not consistent.", text.isEmpty());
        assertEquals("The getNumberOfElements methods are not consistent.", text.toBytes().length, text.getSize());
        logger.info("Completed testIsEmptyAndLengthAndGetNumberOfElements().");
    }


    /**
     * Test the toArray method.
     */
    @Test
    public void testToArray() {
        logger.info("Beginning testToArray()...");
        BinaryString text = new BinaryString("7ZQ8G6ZJZDV8T", 32);
        Byte[] array = text.toArray();
        BinaryString copy = new BinaryString(array);
        assertEquals("The toArray method is not consistent.", text, copy);
        logger.info("Completed testToArray().");
    }


    /**
     * Test the iterator method.
     */
    @Test
    public void testIterator() {
        logger.info("Beginning testIterator()...");
        BinaryString text = new BinaryString("7ZQ8G6ZJZDV8T", 32);
        int count = 0;
        for (Byte b : text) {
            count++;
        }
        assertEquals("The iterator is not consistent.", text.toBytes().length, count);
        logger.info("Completed testIterator().");
    }

}
