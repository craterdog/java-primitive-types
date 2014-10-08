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


/**
 * This class implements a mathematical probability as a primitive type.  The allowed values
 * are double precision floating point numbers in the range [0.0..1.0].  Instances of this
 * class are immutable.
 *
 * @author Derk Norton
 */
public final class Probability implements Comparable<Probability> {

    /*
     * This value is limited to the range [0.0d..1.0d].
     */
    private final double value;


    /**
     * This constructor creates a new instance of a probability with a random value between [0.0..1.0).
     * Notice that the random value can never be 1.0.
     */
    public Probability() {
        this.value = RandomUtils.pickRandomProbability();  // returns [0.0..1.0) so will never be 1.0
    }


    /**
     * This constructor creates a new instance of a probability with the specified value.
     *
     * @param value The value of the new probability [0.0..1.0].
     */
    public Probability(double value) {
        if (value < 0.0d || value > 1.0d)
            throw new NumberFormatException("Attempted to set the value of a probability outside the range of 0.0 - 1.0: " + value);
        this.value = value;
    }


    @Override
    public String toString() {
        return Double.toString(value);
    }


    @Override
    public int compareTo(Probability probability) {
        if (probability == null) return 1;
        return new Double(value).compareTo(probability.value);
    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Probability)) return false;
        Probability that = (Probability) obj;
        return this.value == that.value;
    }


    @Override
    public int hashCode() {
        int hash = new Double(value).hashCode();
        return hash;
    }


    /**
     * This method converts this probability into a boolean value.  If the value is
     * greater than 0.5 then the result is true, and false otherwise.
     *
     * @return The boolean value of this probability.
     */
    public boolean toBoolean() {
        return value > 0.5d;
    }


    /**
     * This method converts this probability into a double value.
     *
     * @return The double value of this probability.
     */
    public double toDouble() {
        return value;
    }


    /**
     * This function returns the result of a coin toss that is weighted with the
     * specified probability.  A probability of zero will always return false and
     * a probability of one will always return true.
     *
     * @param probability The probability of the weighted coin.
     * @return The result of the coin toss.
     */
    static public boolean coinToss(Probability probability) {
        double toss = RandomUtils.pickRandomProbability();  // returns [0.0..1.0) so will never be 1.0
        return probability.value > toss;
    }


    /**
     * This function returns the logical inverse of the specified probability.  The value of the
     * logical inverse of a probability is 1.0 - P.
     *
     * @param probability The probability whose inverse is being determined.
     * @return The logical inverse of the probability.
     */
    static public Probability not(Probability probability) {
        return new Probability(1.0d - probability.value);
    }


    /**
     * This function returns the logical conjunction of the specified probabilities.  The value
     * of the logical conjunction of two probabilities is P * Q.
     *
     * @param probability1 The first probability.
     * @param probability2 The second probability.
     * @return The logical conjunction of the two probabilities.
     */
    static public Probability and(Probability probability1, Probability probability2) {
        return new Probability(probability1.value * probability2.value);
    }


    /**
     * This function returns the material nonimplication of the specified probabilities.  The value
     * of the material nonimplication of two probabilities is P and not(Q).
     *
     * @param probability1 The first probability.
     * @param probability2 The second probability.
     * @return The material nonimplication of the two probabilities.
     */
    static public Probability sans(Probability probability1, Probability probability2) {
        return and(probability1, not(probability2));
    }


    /**
     * This function returns the logical disjunction of the specified probabilities.  The value
     * of the logical disjunction of two probabilities is P + Q - and(P, Q).
     *
     * @param probability1 The first probability.
     * @param probability2 The second probability.
     * @return The logical disjunction of the two probabilities.
     */
    static public Probability or(Probability probability1, Probability probability2) {
        return new Probability(probability1.value + probability2.value - and(probability1, probability2).value);
    }


    /**
     * This function returns the logical exclusive disjunction of the specified probabilities.  The value
     * of the logical exclusive disjunction of two probabilities is sans(P, Q) + sans(Q, P).
     *
     * @param probability1 The first probability.
     * @param probability2 The second probability.
     * @return The logical exclusive disjunction of the two probabilities.
     */
    static public Probability xor(Probability probability1, Probability probability2) {
        return new Probability(sans(probability1, probability2).value + sans(probability2, probability1).value);
    }

}
