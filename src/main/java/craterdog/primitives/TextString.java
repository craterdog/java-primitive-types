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

import craterdog.core.Sequential;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * This class implements a text string that behaves like a <code>java.lang.String</code> but
 * that also supports the <code>java.lang.Iterable</code> interface allowing it to be used
 * in a java language for each loop.  Text strings are immutable.
 *
 * @author Derk Norton
 */
public final class TextString implements Comparable<TextString>, CharSequence, Sequential<Character> {

    private final String value;


    /**
     * This default constructor creates an empty text string.
     */
    public TextString() {
        this.value = "";
    }


    /**
     * This constructor creates a text string using the specified string value.
     * @param value The value to be used to seed the new text string.
     */
    public TextString(String value) {
        this.value = value;
    }


    /**
     * This constructor creates a text string using the specified character sequence.
     * @param value The character sequence be used to seed the new text string.
     */
    public TextString(CharSequence value) {
        this.value = value.toString();
    }


    /**
     * This constructor creates a text string using the specified character array.
     * @param value The character array be used to seed the new text string.
     */
    public TextString(char[] value) {
        this.value = String.copyValueOf(value);
    }


    /**
     * This constructor creates a text string using the specified character array.
     * @param value The character array be used to seed the new text string.
     */
    public TextString(Character[] value) {
        int size = value.length;
        char[] array = new char[size];
        for (int i = 0; i < size; i++) {
            array[i] = value[i];
        }
        this.value = new String(array);
    }


    @Override
    public int compareTo(TextString that) {
        if (that == null) return 1;
        if (this == that) return 0;  // same object
        return this.value.compareTo(that.value);
    }


    @Override
    public boolean equals(Object object) {
        if (object == null || !(object instanceof TextString)) return false;
        TextString that = (TextString) object;
        if (this == that) return true;  // same object
        return this.value.equals(that.value);
    }


    @Override
    public int hashCode() {
        return value.hashCode();
    }


    @Override
    public String toString() {
        return value;
    }


    @Override
    public int length() {
        return value.length();
    }


    @Override
    public char charAt(int index) {
        return value.charAt(index);
    }


    @Override
    public CharSequence subSequence(int start, int end) {
        return new TextString(value.subSequence(start, end));
    }


    @Override
    public boolean isEmpty() {
        return value.isEmpty();
    }


    @Override
    public int getNumberOfElements() {
        return value.length();
    }


    @Override
    public Character[] toArray() {
        Character[] array = new Character[value.length()];
        int index = 0;
        for (Character c : this) {
            array[index++] = c;
        }
        return array;
    }


    @Override
    public Iterator<Character> iterator() {
        return new TextIterator();
    }


    private final class TextIterator implements Iterator<Character> {

        int index;

        private TextIterator() {
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < length();
        }

        @Override
        public Character next() {
            if (index == length()) throw new NoSuchElementException();
            Character element = value.charAt(index++);
            return element;
        }

    }

}
