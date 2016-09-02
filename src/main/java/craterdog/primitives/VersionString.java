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
import java.util.Arrays;
import java.util.NoSuchElementException;


/**
 * This class defines a primitive type that captures a version string.
 * Version numbers use dot notation and can be arbitrarily deep (e.g. 1, 1.3, 24.7.5, etc.).
 * A version number can never have a zero in it (e.g. 0.3, 1.0, 4.0.7, etc.).
 *
 * @author Derk Norton
 */
public final class VersionString extends Primitive<VersionString> implements Sequential<Integer> {

    /**
     * An array of the version numbers in the version string.
     */
    private int[] value;


    /**
     * This constructor creates a version number from an arbitrary list of version numbers.
     *
     * @param value The list of version numbers (can't be empty and each must be
     * greater than zero).
     */
    public VersionString(int... value) {
        this.value = value;
        if (value.length == 0) throw new NumberFormatException("The version number cannot be empty.");
        for (int version : value) {
            if (version < 1) throw new NumberFormatException("Version numbers must be greater than zero: " + toString());
        }
    }


    public VersionString(String value) {
        String[] integers = value.split("\\.");
        int count = integers.length;
        if (count == 0) throw new NumberFormatException("The version number cannot be empty.");
        this.value = new int[count];
        for (int i = 0; i < count; i++) {
            this.value[i] = Integer.parseInt(integers[i]);
        }
        for (int version : this.value) {
            if (version < 1) throw new NumberFormatException("Version numbers must be greater than zero: " + toString());
        }
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int version : value) {
            builder.append(version);
            builder.append(".");
        }
        return builder.substring(0, builder.length() - 1);  // trim off the last "."
    }


    @Override
    public int compareTo(VersionString that) {
        if (that == null) return 1;  // everything is greater than null
        if (this == that) {
            return 0;
        }
        int shortestLength = Math.min(this.value.length, that.value.length);
        for (int i = 0; i < shortestLength; i++) {
            if (this.value[i] < that.value[i]) return -1;
            if (this.value[i] > that.value[i]) return 1;
        }
        if (this.value.length < that.value.length) return -1;
        if (this.value.length > that.value.length) return 1;
        return 0;
    }


    @Override
    public Iterator<Integer> createIterator() {
        return new VersionIterator();
    }


    @Override
    public int getSize() {
        return value.length;
    }


    @Override
    public Integer[] toArray() {
        Integer[] array = new Integer[value.length];
        int index = 0;
        for (Integer i : this) {
            array[index++] = i;
        }
        return array;
    }


    /**
     * This function returns the next version number in the series for the specified version.
     * If the specified version is "1", the next version would be "2".  If the specified
     * version is "2.5.7", the next version would be "2.5.8".
     *
     * @param version The current version.
     * @return The next version in the series.
     */
    static public VersionString getNextVersion(VersionString version) {
        int length = version.value.length;
        int[] nextVersion = Arrays.copyOf(version.value, length);
        nextVersion[length - 1]++;  // increment the last version number
        return new VersionString(nextVersion);
    }


    /**
     * This method returns a new version number in the series by incrementing the version
     * at the specified depth and truncating the rest of the version numbers.  If the
     * current version is "2.5.7" and the depth is 2, the next version would be "2.6". If
     * the current version is "1.22" and the depth is 3, the next version would be "1.22.1".
     *
     * @param version The current version.
     * @param depth The depth of the version number to be incremented.
     * @return The new version number.
     */
    static public VersionString getNewVersion(VersionString version, int depth) {
        if (depth < 1 || depth > version.value.length + 1) throw new NumberFormatException("The depth cannot be less than zero or greater than the depth of the current version number: " + depth);
        int[] newVersion = Arrays.copyOf(version.value, depth);  // only copy to the depth
        newVersion[depth - 1]++;  // increment the specified version number
        return new VersionString(newVersion);
    }


    private final class VersionIterator extends Iterator<Integer> {

        int index;

        private VersionIterator() {
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
                this.index = value.length + index;  // index from end of array
            }
        }

        @Override
        public void toEnd() {
            this.index = value.length;
        }

        @Override
        public boolean hasPrevious() {
            return index > 0;
        }

        @Override
        public Integer getPrevious() {
            if (index == 0) throw new NoSuchElementException();
            Integer element = value[--index];
            return element;
        }

        @Override
        public boolean hasNext() {
            return index < value.length;
        }

        @Override
        public Integer getNext() {
            if (index == value.length) throw new NoSuchElementException();
            Integer element = value[index++];
            return element;
        }

    }

}