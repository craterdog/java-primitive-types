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

import craterdog.core.Iterator;
import craterdog.core.Sequential;
import java.util.NoSuchElementException;


/**
 * This class implements a text string that behaves like a <code>java.lang.String</code> but
 * that also supports the <code>java.lang.Iterable</code> interface allowing it to be used
 * in a java language for each loop.  Text strings are immutable.
 *
 * @author Derk Norton
 */
public final class TextString extends Primitive<TextString> implements CharSequence, Sequential<Character> {

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
    public Iterator<Character> createIterator() {
        return new TextIterator();
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
        return Sequential.super.isEmpty();
    }


    @Override
    public int getSize() {
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


    private final class TextIterator extends Iterator<Character> {

        int index;

        private TextIterator() {
            this.index = 0;
        }

        @Override
        public void toStart() {
            this.index = 0;
        }

        @Override
        public void toIndex(int index) {
            if (index > 0) {
                this.index = index - 1;  // convert to ordinal indexing
            } else {
                this.index = value.length() + index;  // index from end of bytes
            }
        }

        @Override
        public void toEnd() {
            this.index = value.length();
        }

        @Override
        public boolean hasPrevious() {
            return index > 0;
        }

        @Override
        public Character getPrevious() {
            if (index == 0) throw new NoSuchElementException();
            Character element = value.charAt(--index);
            return element;
        }

        @Override
        public boolean hasNext() {
            return index < value.length();
        }

        @Override
        public Character getNext() {
            if (index == value.length()) throw new NoSuchElementException();
            Character element = value.charAt(index++);
            return element;
        }

    }

}
