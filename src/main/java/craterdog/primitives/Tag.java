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

import craterdog.utils.Base32Utils;
import craterdog.utils.ByteUtils;
import craterdog.utils.RandomUtils;


/**
 * This class provides a globally unique identifier that can be used to reference anything
 * without requiring a centralized generator.  The tag is "self hashing" making it very
 * efficient as a key.
 *
 * @author Derk Norton
 */
public final class Tag implements Comparable<Tag> {

    static private final int DEFAULT_TAG_SIZE = 20;

    private final int tagSize;
    private final int hash;
    private final String string;


    /**
     * This default constructor creates an instance of a tag with a new random value.
     */
    public Tag() {
        tagSize = DEFAULT_TAG_SIZE;
        byte[] bytes = RandomUtils.generateRandomBytes(tagSize);
        hash = ByteUtils.bytesToInt(bytes);
        string = Base32Utils.encode(bytes);
    }


    /**
     * This constructor creates an instance of a new tag with the specified number of bytes.
     *
     * @param tagSizeInBytes The number of bytes that should be used to define the new tag.
     */
    public Tag(int tagSizeInBytes) {
        tagSize = tagSizeInBytes;
        byte[] bytes = RandomUtils.generateRandomBytes(tagSize);
        hash = ByteUtils.bytesToInt(bytes);
        string = Base32Utils.encode(bytes);
    }


    /**
     * This constructor creates an instance of a tag with the specified value.
     *
     * @param bytes The initial value of the tag as a byte array.
     */
    public Tag(byte[] bytes) {
        tagSize = bytes.length;
        hash = ByteUtils.bytesToInt(bytes);
        string = Base32Utils.encode(bytes);
    }


    /**
     * This constructor creates an instance of a tag with the specified value.
     *
     * @param value The initial value of the tag as a base 32 string.
     */
    public Tag(String value) {
        try {
            string = value;
            byte[] bytes = Base32Utils.decode(string);
            tagSize = bytes.length;
            hash = ByteUtils.bytesToInt(bytes);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(value, e);
        }
    }


    /**
     * Returns a human readable version of this tag.
     * @return A string containing a modified base 32 encoding of the bytes that make up this tag.
     */
    @Override
    public String toString() {
        return string;
    }


    /**
     * Returns the byte array for this tag.
     * @return The byte array for this tag.
     */
    public byte[] toBytes() {
        return Base32Utils.decode(string);
    }


    /**
     * Returns an integer hash value of the bytes that make up this tag.  Since the bytes are a
     * generated crypto-random sequence, the first four bytes make a perfect hash value.  No
     * additional processing is necessary.
     *
     * @return The integer hash value for the tag.
     */
    @Override
    public int hashCode() {
        return hash;
    }


    /**
     * Returns true if this tag is equal to the specified tag and false otherwise.
     * Two tag are equal if they have exactly the same bytes.
     *
     * @param object The tag to be compared.
     * @return true if the tags are equal and false otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Tag)) return false;
        Tag that = (Tag) object;
        if (this.hash != that.hash) return false;
        return this.string.equals(that.string);
    }


    /**
     * Returns an integer (signum) that tells whether this tag is greater than (1), less
     * than (-1), or equal to (0) the specified tag.
     *
     * @param tag The tag with which to be compared.
     * @return The resulting signum value.
     */
    @Override
    public int compareTo(Tag tag) {
        return Integer.signum(this.string.compareTo(tag.string));
    }


    /**
     * This function calculates the size of the string that will be generated for a specific tag size.
     *
     * @param tagSize The number of bytes in the tag.
     * @return The number of characters in the corresponding string representation of the tag.
     */
    static public int calculateStringSize(int tagSize) {
        int numberOfBits = tagSize * 8;
        int stringSize = numberOfBits / 5;
        stringSize += numberOfBits % 5 > 0 ? 1 : 0;
        return stringSize;
    }

}
