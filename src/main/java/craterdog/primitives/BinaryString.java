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

import craterdog.core.Composite;
import craterdog.core.Sequential;
import craterdog.utils.Base02Utils;
import craterdog.utils.Base16Utils;
import craterdog.utils.Base32Utils;
import craterdog.utils.Base64Utils;
import craterdog.utils.ByteUtils;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * This class implements a binary string in a way that makes it easy to encode and decode it
 * in various numeric bases (2, 16, 32, 64). The class also supports the
 * <code>java.lang.Iterable</code> interface so that it can be used by the java language
 * for each loops. Binary strings are immutable.
 *
 * @author Derk Norton
 */
public final class BinaryString implements Comparable<BinaryString>, Sequential<Byte>, Composite {

    private final byte[] bytes;


    /**
     * This default constructor creates an empty binary string.
     */
    public BinaryString() {
        this.bytes = new byte[0];
    }


    /**
     * This constructor creates a binary string containing the specified bytes.
     *
     * @param bytes The bytes to be used to seed the binary string.
     */
    public BinaryString(byte[] bytes) {
        this.bytes = Arrays.copyOf(bytes, bytes.length);
    }


    /**
     * This constructor creates a binary string containing the specified bytes.
     *
     * @param bytes The bytes to be used to seed the binary string.
     */
    public BinaryString(Byte[] bytes) {
        int size = bytes.length;
        this.bytes = new byte[size];
        for (int i = 0; i < size; i++) {
            this.bytes[i] = bytes[i];
        }
    }


    /**
     * This constructor decodes the specified string using the specified base to extract the
     * corresponding binary string.
     *
     * @param string The encoded string containing the bytes to be used to seed the binary string.
     * @param base The numeric base (2, 16, 32, or 64) that was used to encode the string.
     */
    public BinaryString(String string, int base) {
        switch (base) {
            case 2:
                this.bytes = Base02Utils.decode(string);
                break;
            case 16:
                this.bytes = Base16Utils.decode(string);
                break;
            case 32:
                this.bytes = Base32Utils.decode(string);
                break;
            case 64:
                this.bytes = Base64Utils.decode(string);
                break;
            default:
                throw new NumberFormatException("Base " + base + " binary strings not supported.");
        }
    }


    /**
     * This constructor creates a binary string containing the specified bit set.
     *
     * @param bits The bit set to be used to seed the binary string.
     */
    public BinaryString(BitSet bits) {
        this.bytes = bits.toByteArray();
    }


    @Override
    public int compareTo(BinaryString that) {
        if (that == null) return 1;
        if (this == that) return 0;  // same object
        int result = 0;
        Iterator<Byte> thisIterator = this.iterator();
        Iterator<Byte> thatIterator = that.iterator();
        while (thisIterator.hasNext() && thatIterator.hasNext()) {
            Byte thisElement = thisIterator.next();
            Byte thatElement = thatIterator.next();
            result = Integer.compare(
                    ByteUtils.byteToUnsigned(thisElement),
                    ByteUtils.byteToUnsigned(thatElement)
            );
            if (result != 0) break;
        }
        if (result == 0) {
            // same so far, check for different lengths
            result = Integer.compare(this.getNumberOfElements(), that.getNumberOfElements());
        }
        return result;
    }


    @Override
    public boolean equals(Object object) {
        if (object == null || !(object instanceof BinaryString)) return false;
        BinaryString that = (BinaryString) object;
        if (this == that) return true;  // same object
        return Arrays.equals(this.bytes, that.bytes);
    }


    @Override
    public int hashCode() {
        return Arrays.hashCode(bytes);
    }


    @Override
    public String toString() {
        return toString(64);
    }


    @Override
    public String toString(String indentation) {
        return toString(64, indentation);
    }


    /**
     * This method converts the binary string into the specified base encoded string. If the
     * encoded string is longer than 80 characters, it is broken up into separate lines with
     * a maximum of 80 characters per line.
     *
     * @param base The base to be used for encoding the bytes (2, 16, 32, 64).
     * @return The base 64 encoded string for the binary string.
     */
    public String toString(int base) {
        return toString(base, "");
    }


    /**
     * This method converts the binary string into the specified base encoded string. If the
     * encoded string is longer than 80 characters, it is broken up into separate lines with
     * a maximum of 80 characters per line.  The specified whitespace indentation string is
     * used to prefix each line.
     *
     * @param base The base to be used for encoding the bytes (2, 16, 32, 64).
     * @param indentation The (whitespace only) string that should be used to prefix each line.
     * @return The base 64 encoded string for the binary string.
     */
    public String toString(int base, String indentation) {
        switch (base) {
            case 2:
                return Base02Utils.encode(bytes, indentation);
            case 16:
                return Base16Utils.encode(bytes, indentation);
            case 32:
                return Base32Utils.encode(bytes, indentation);
            case 64:
                return Base64Utils.encode(bytes, indentation);
            default:
                throw new NumberFormatException("Base " + base + " binary strings not supported.");
        }
    }


    /**
     * This method returns a byte array containing the binary string.
     *
     * @return A byte array containing the binary string.
     */
    public byte[] toBytes() {
        return Arrays.copyOf(bytes, bytes.length);
    }


    @Override
    public boolean isEmpty() {
        return bytes.length == 0;
    }


    @Override
    public int getNumberOfElements() {
        return bytes.length;
    }


    @Override
    public Byte[] toArray() {
        Byte[] array = new Byte[bytes.length];
        int index = 0;
        for (Byte b : this) {
            array[index++] = b;
        }
        return array;
    }


    @Override
    public Iterator<Byte> iterator() {
        return new BinaryIterator();
    }


    private final class BinaryIterator implements Iterator<Byte> {

        int index;

        private BinaryIterator() {
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < bytes.length;
        }

        @Override
        public Byte next() {
            if (index == bytes.length) throw new NoSuchElementException();
            Byte element = bytes[index++];
            return element;
        }

    }

}
