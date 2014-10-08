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


/**
 * This class encapsulates radian based angles.
 *
 * @author Derk Norton
 */
public final class Angle implements Comparable<Angle> {

    /*
     * This constant defines how close two angles need to be before they are considered the same.
     */
    static final double EPSILON = 0.000000000000001d;

    private final double value;


    /**
     * This constant approximates the value of pi.
     */
    static public final Angle PI = new Angle(Math.PI);


    /**
     * This default constructor creates an angle with a default value of zero radians.
     */
    public Angle() {
        this.value = 0.0d;
    }


    /**
     * This constructor creates an angle with the specified value in radians.  If the value
     * is outside the range (-pi..pi] then the value is normalize to be within this range.
     *
     * @param value The value of the new angle.
     */
    public Angle(double value) {
        this.value = normalize(value);
    }


    @Override
    public int compareTo(Angle angle) {
        if (angle == null) return 1;
        return new Double(value).compareTo(angle.value);
    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Angle)) return false;
        Angle that = (Angle) obj;
        return value - that.value < Angle.EPSILON;
    }


    @Override
    public int hashCode() {
        return new Double(value).hashCode();
    }


    @Override
    public String toString() {
        return Double.toString(value);
    }


    /**
     * This method returns the value of the angle as a double.
     *
     * @return The value of the angle.
     */
    public double toDouble() {
        return value;
    }


    /**
     * This function returns the normalized inversion of the specified angle.  The value of
     * the inverted angle is angle - pi.
     *
     * @param angle The angle.
     * @return The inverted angle.
     */
    static public Angle invert(Angle angle) {
        return new Angle(angle.value - Math.PI);
    }


    /**
     * This function returns the normalized negation of the specified angle.  The value of
     * the negated angle is -angle.
     *
     * @param angle The angle.
     * @return The negated angle.
     */
    static public Angle negate(Angle angle) {
        return new Angle(-angle.value);
    }


    /**
     * This function returns the normalized sum of two angles.
     *
     * @param angle1 The first angle.
     * @param angle2 The second angle.
     * @return The sum of the two angles.
     */
    static public Angle sum(Angle angle1, Angle angle2) {
        return new Angle(angle1.value + angle2.value);
    }


    /**
     * This function returns the normalized difference of two angles.
     *
     * @param angle1 The first angle.
     * @param angle2 The second angle.
     * @return The difference of the two angles.
     */
    static public Angle difference(Angle angle1, Angle angle2) {
        return new Angle(angle1.value - angle2.value);
    }


    /**
     * This function returns the normalized product of an angle and a scalar value.
     *
     * @param angle The angle.
     * @param multiplier The scalar value.
     * @return The product of the angle and scalar value.
     */
    static public Angle product(Angle angle, double multiplier) {
        return new Angle(angle.value * multiplier);
    }


    /**
     * This function returns the normalized quotient of an angle and a scalar value.
     *
     * @param angle The angle.
     * @param divisor The scalar value.
     * @return The quotient of the angle and scalar value.
     */
    static public Angle quotient(Angle angle, double divisor) {
        return new Angle(angle.value / divisor);
    }


    /**
     * This function returns the sine of the specified angle.
     *
     * @param angle The angle.
     * @return The sine of the angle.
     */
    static public double sine(Angle angle) {
        double result = lock(Math.sin(angle.value));
        return result;
    }


    /**
     * This function returns the angle whose sine is the specified value.
     *
     * @param value The value.
     * @return The arcsine of the value.
     */
    static public Angle arcsine(double value) {
        return new Angle(Math.asin(value));
    }


    /**
     * This function returns the cosine of the specified angle.
     *
     * @param angle The angle.
     * @return The cosine of the angle.
     */
    static public double cosine(Angle angle) {
        double result = lock(Math.cos(angle.value));
        return result;
    }


    /**
     * This function returns the angle whose cosine is the specified value.
     *
     * @param value The value.
     * @return The arccosine of the value.
     */
    static public Angle arccosine(double value) {
        return new Angle(Math.acos(value));
    }


    /**
     * This function returns the tangent of the specified angle.
     *
     * @param angle The angle.
     * @return The tangent of the angle.
     */
    static public double tangent(Angle angle) {
        double result = lock(Math.tan(angle.value));
        return result;
    }


    /**
     * This function returns the angle whose tangent is the specified value.
     *
     * @param value The value.
     * @return The arctangent of the value.
     */
    static public Angle arctangent(double value) {
        return new Angle(Math.atan(value));
    }


    /**
     * This function returns the angle whose tangent is the ration of the specified values.
     *
     * @param y The y coordinate.
     * @param x The x coordinate.
     * @return The arctangent of the ratio.
     */
    static public Angle arctangent(double y, double x) {
        return new Angle(Math.atan2(y, x));
    }


    /*
     * This function normalizes the value of an angle to be in the range [0.0..1.0).
     */
    static private double normalize(double value) {
        if (Double.isInfinite(value) || Double.isNaN(value))
            throw new RuntimeException("Attempted to normalize an illegal angle value: " + Double.toString(value));
        double twoPi = 2.0d * Math.PI;
        double result = value % twoPi;  // make in range (-2pi..2pi)
        if (result > Math.PI) {
            result -= twoPi;  // make in the range (-pi..pi]
        }
        if (result <= -Math.PI) {
            result += twoPi;  // make in the range (-pi..pi]
        }
        return result;
    }


    /*
     * This function locks the value of an angle to specific well known angles.
     */
    static private double lock(double value) {
        if (value <= -1.633123935319537E16d) return Double.NEGATIVE_INFINITY;  // lock onto -infinity
        if (value >=  1.633123935319537E16d) return Double.POSITIVE_INFINITY;  // lock onto  infinity
        if (value > -Math.PI - EPSILON && value < -Math.PI + EPSILON) return -Math.PI;  // lock onto -pi
        if (value >  Math.PI - EPSILON && value <  Math.PI + EPSILON) return  Math.PI;  // lock onto  pi
        if (value > -Math.E - EPSILON && value < -Math.E + EPSILON) return -Math.E;  // lock onto -e
        if (value >  Math.E - EPSILON && value <  Math.E + EPSILON) return  Math.E;  // lock onto  e
        if (value > -1.0d - EPSILON && value < -1.0d + EPSILON) return -1.0d;  // lock onto -1
        if (value >  1.0d - EPSILON && value <  1.0d + EPSILON) return  1.0d;  // lock onto  1
        if (value <= 1.2246467991473532E-16d && value >= -1.2246467991473532E-16d) return 0.0d;  // lock onto zero
        return value;
    }

}
