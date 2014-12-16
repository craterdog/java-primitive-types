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
 * This class implements a mathematical probability as a primitive type.  The allowed values
 * are double precision floating point numbers in the range [0.0..1.0].  Instances of this
 * class are immutable.
 *
 * @author Derk Norton
 */
public final class BinaryString implements Comparable<BinaryString>, Sequential<Byte>, Composite {

    private final byte[] bytes;


    public BinaryString() {
        this.bytes = new byte[0];
    }


    public BinaryString(byte[] bytes) {
        this.bytes = Arrays.copyOf(bytes, bytes.length);
    }


    public BinaryString(Byte[] bytes) {
        int size = bytes.length;
        this.bytes = new byte[size];
        for (int i = 0; i < size; i++) {
            this.bytes[i] = bytes[i];
        }
    }


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


    public BinaryString(BitSet bits) {
        this.bytes = bits.toByteArray();
    }


    @Override
    public String toString() {
        return toBase64String();
    }


    @Override
    public String toString(String indentation) {
        return toBase64String(indentation);
    }


    public String toBase02String() {
        return Base02Utils.encode(bytes);
    }


    public String toBase02String(String indentation) {
        return Base02Utils.encode(bytes, indentation);
    }


    public String toBase16String() {
        return Base16Utils.encode(bytes);
    }


    public String toBase16String(String indentation) {
        return Base16Utils.encode(bytes, indentation);
    }


    public String toBase32String() {
        return Base32Utils.encode(bytes);
    }


    public String toBase32String(String indentation) {
        return Base32Utils.encode(bytes, indentation);
    }


    public String toBase64String() {
        return Base64Utils.encode(bytes);
    }


    public String toBase64String(String indentation) {
        return Base64Utils.encode(bytes, indentation);
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
    public boolean isEmpty() {
        return bytes.length == 0;
    }


    @Override
    public int getNumberOfElements() {
        return bytes.length;
    }


    public byte[] getBytes() {
        return Arrays.copyOf(bytes, bytes.length);
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
    @Deprecated
    public void toArray(Byte[] array) {
        throw new UnsupportedOperationException("This operation has been deprecated.");
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
