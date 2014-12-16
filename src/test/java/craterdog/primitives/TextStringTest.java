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
 * This class performs unit tests on the <code>TextString</code> class.
 *
 * @author Derk Norton
 */
public class TextStringTest {

    static private final XLogger logger = XLoggerFactory.getXLogger(TextStringTest.class);


    /**
     * Log a message at the beginning of the tests.
     */
    @BeforeClass
    public static void setUpClass() {
        logger.info("Running TextString Unit Tests...\n");
    }


    /**
     * Log a message at the end of the tests.
     */
    @AfterClass
    public static void tearDownClass() {
        logger.info("Completed TextString Unit Tests.\n");
    }


    /**
     * Test the constructor that takes a string and compare it to the result of the toString
     * method.
     */
    @Test
    public void testConstructorAndToString() {
        logger.info("Beginning testConstructorAndToString()...");
        String string = "This is a text string.";
        TextString text = new TextString(string);
        assertEquals("The constructor and equals methods are incompatible.", string, text.toString());
        logger.info("Completed testConstructorAndToString().");
    }


    /**
     * Test the equals and compareTo methods.
     */
    @Test
    public void testEqualsAndCompareTo() {
        logger.info("Beginning testEqualsAndCompareTo()...");
        TextString nullText = null;
        TextString emptyText = new TextString(new char[0]);
        TextString lowerText = new TextString("This is a string...");
        TextString higherText = new TextString("This is a text string...");
        assert !emptyText.equals(nullText);
        assert emptyText.compareTo(nullText) > 0;
        assert emptyText.equals(emptyText);
        assert emptyText.compareTo(emptyText) == 0;
        assert !lowerText.equals(higherText);
        assert lowerText.compareTo(emptyText) > 0;
        assert lowerText.compareTo(higherText) < 0;
        logger.info("Completed testEqualsAndCompareTo().");
    }


    /**
     * Test the hashCode method.
     */
    @Test
    public void testHashCode() {
        logger.info("Beginning testHashCode()...");
        String string = "This a text string...";
        TextString text = new TextString(string);
        assertEquals("The hashCode methods are not consistent.", string.hashCode(), text.hashCode());
        logger.info("Completed testHashCode().");
    }


    /**
     * Test the isEmpty, length and getNumberOfElements methods.
     */
    @Test
    public void testIsEmptyAndLengthAndGetNumberOfElements() {
        logger.info("Beginning testIsEmptyAndLengthAndGetNumberOfElements()...");
        String string = "";
        TextString text = new TextString(string);
        assertTrue("The isEmpty methods are not consistent.", text.isEmpty());
        assertEquals("The length methods are not consistent.", string.length(), text.length());
        assertEquals("The getNumberOfElements methods are not consistent.", string.length(), text.getNumberOfElements());

        string = "This a text string...";
        text = new TextString(string);
        assertFalse("The isEmpty methods are not consistent.", text.isEmpty());
        assertEquals("The length methods are not consistent.", string.length(), text.length());
        assertEquals("The getNumberOfElements methods are not consistent.", string.length(), text.getNumberOfElements());
        logger.info("Completed testIsEmptyAndLengthAndGetNumberOfElements().");
    }


    /**
     * Test the charAt and subSequence methods.
     */
    @Test
    public void testCharAtAndSubSequence() {
        logger.info("Beginning testCharAtAndSubSequence()...");
        String string = "This a text string...";
        TextString text = new TextString(string);
        assertEquals("The charAt methods are not consistent.", string.charAt(2), text.charAt(2));

        string = "This a text string...";
        text = new TextString(string);
        assertEquals("The subSequence methods are not consistent.", string.subSequence(5, 8).toString(), text.subSequence(5, 8).toString());
        logger.info("Completed testCharAtAndSubSequence().");
    }


    /**
     * Test the toArray method.
     */
    @Test
    public void testToArray() {
        logger.info("Beginning testToArray()...");
        String string = "This a text string...";
        TextString text = new TextString(string);
        Character[] array = text.toArray();
        TextString copy = new TextString(array);
        assertEquals("The toArray method is not consistent.", text, copy);
        logger.info("Completed testToArray().");
    }


    /**
     * Test the iterator method.
     */
    @Test
    public void testIterator() {
        logger.info("Beginning testIterator()...");
        String string = "This a text string...";
        TextString text = new TextString(string);
        int count = 0;
        for (Character c : text) {
            count++;
        }
        assertEquals("The iterator is not consistent.", string.length(), count);
        logger.info("Completed testIterator().");
    }

}
