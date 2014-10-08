#Java Primitive Types

This project contains a set of Java primitive type classes. Each class follows the same immutability
conventions that the native Java primitive types support. They also provide a default implementation
of the canonical `Object` class methods. The following primitive types are included in this project:

 * *Angle* - representing the mathematical concept of an angle in radians
 * *Probability* - representing a mathematical probability in the range [0.0..1.0)
 * *Tag* - a unique identifier of any length displayed in base 32 format

To get started using these classes, include the following dependency in your maven pom.xml file:

```xml
    <dependency>
        <groupId>com.craterdog</groupId>
        <artifactId>java-primitive-types</artifactId>
        <version>3.0</version>
    </dependency>
```

##Angle Primitive Type

The `Angle` class provides some static methods that implement functions involving
angles. The following functions are supported:

 * invert
 * negate
 * sum
 * difference
 * product
 * quotient
 * sine
 * arcsine
 * cosine
 * arccosine
 * tangent
 * arctangent

##Probability Primitive Type

The `Probability` class provides some static methods that implement functions involving
probabilities. The following functions are supported:

 * coinToss
 * not
 * and
 * sans (equivalent to: a and not b)
 * or
 * xor

##Tag Primitive Type

The `Tag` class provides a way to encapsulate an arbitrary number of random bytes in
a way that makes it easy to view them.  The following shows some examples of tags:

    Tag size 0: 
    Tag size 1: 2R
    Tag size 2: 5V00
    Tag size 3: 03V82
    Tag size 4: PVJ9CR0
    Tag size 5: WHQGXSY4
    Tag size 6: S23BSNPK7W
    Tag size 7: T181GFCBAXFH
    Tag size 8: 2PP8JYQVZBRG8
    Tag size 9: LJSDJCVPVSKP1KR
    Tag size 10: VZAV0H0NR87H1XWF
    Tag size 11: FC8QDCA2GPA27SDLTW
    Tag size 12: DP02NM4W1F3B2J8XC5XH
    Tag size 13: APJVMY7PTXA0DQY9V4HS2
    Tag size 14: ZVHQFXW1NYDMRK7RHVMA5N0
    Tag size 15: 2DGHDSYPK1WY1AZPL21G1NPW
    Tag size 16: 4C6CZVXZRT0YFBGTD9WXZQWT4W
    Tag size 17: 9T5CAQDTMH0QK2GAA9WV36BLJJCH
    Tag size 18: 6M83R50BFRDDKQTJN5N4VC5YM16R4
    Tag size 19: 4Y4PXH78JQ9A0P59XCJSP8D0KN0A93H
    Tag size 20: 8262KJCK8HRR08G10M5X7HY15X91RTYF
    Tag size 21: SXZDNTBDNGYYKX03YHTWAAY2AYB4NPVNC0
    Tag size 22: P0JKL0W6Q71N7RLGR2V3JN5X6W5R33BMCLBH
    Tag size 23: XGM8H5TN9A1QKFHHFG1HSXM1D7Z036X79W41W
    Tag size 24: KTASZ0HR4YZ5GSCBCYPGQ3TFJ5R8YRWC1VAHP48
    Tag size 25: LB4RZMAJ6H1T8M1X3MZGLW1R2XH192FZ1YJTPBR7
    Tag size 26: XYMKTQFYPVA1D581LKBX9KKFPV4VR1015CBF09Q3D4
    Tag size 27: A5C2B4LMFNQZF508FGFC44C92YCFT5G0BYB8B5MCJN70
    Tag size 28: N9HN7PN38N3GD26DMC5H288AQ55VVNBLC9J3GZATPJTJY
    Tag size 29: GSBT2VRZKHVCC8A50LG5BWCZTA0151Z2PVF3BPJHPQGYKQ0
    Tag size 30: 6QPDCY5CVJJFLHJWAJ8WRJ800PNYJMV4ZN1NJWT1SCTLYTYP
    Tag size 31: L0G62RB6DQ8SC02BKY08S1AY35MQ34Z7Q7XFVK7CLP03LPWXN8
    Tag size 32: AS5RH03BP0ACP5H980J3MQZNPAK6D3L4P4Z2GH20R052HB8NC160

Each tag is formatted as a base 32 ('0'..'9', 'A'..'D', 'F'..'H', 'J'..'N', 'P'..'T', 'V'..'Z')
string representation of the random bytes that make up the tag. The bigger the tag the less
likely there will be a collision. The following table captures the uniqueness:

Scope    | # Things  | # Bytes | # Bits | # Characters | Size of Space
-------- | --------- | ------- | ------ | ------------ | -------------
Desk     | 16        | 1       | 8      | 2            | 256
Room     | 256       | 2       | 16     | 4            | 65536
Building | 65536     | 4       | 32     | 7            | 4.29E+009
City     | 4.29E+009 | 8       | 64     | 13           | 1.84E+019
Globe    | 1.84E+019 | 16      | 128    | 26           | 3.40E+038
Galaxy   | 3.40E+038 | 32      | 256    | 52           | 1.16E+077
Universe | 1.16E+077 | 64      | 512    | 103          | 1.34E+154

Use this table to chose the size of the tag that fits the scope of your name space.

##Example Code
The following example code demonstrates some of the ways the `Angle` primitive class can be used:

```java
    // Create some angles
    Angle pi = Angle.PI;
    Angle piOver2 = new Angle(Math.PI / 2.0d);
    Angle piOver3 = Angle.quotient(pi, 3.0d);
    Angle piOver4 = Angle.quotient(piOver2, 2.0d);
    Angle piOver6 = Angle.product(piOver3, 0.5d);

    // Do some angle calculations
    log.info("The value of pi is: {}", pi);
    log.info("The value of pi/2 is: {}", piOver2);
    log.info("The value of pi/3 is: {}", piOver3);
    log.info("The value of pi/4 is: {}", piOver4);
    log.info("The value of pi/6 is: {}", piOver6);
    log.info("The negative of pi/6 is: {}", Angle.negate(piOver6));
    log.info("The inversion of pi/3 is: {}", Angle.invert(piOver3));
    log.info("The sum of pi/6 and pi/3 is: {}", Angle.sum(piOver6, piOver3));
    log.info("The difference of pi/3 and pi/2 is: {}", Angle.difference(piOver3, piOver2));
    log.info("The sine of pi/6 is: {}", Angle.sine(piOver6));
    log.info("The cosine of pi/3 is: {}", Angle.cosine(piOver3));
    log.info("The tangent of pi/2 is: {}", Angle.tangent(piOver2));
    log.info("The arctangent of 1.0 is: {}", Angle.arctangent(1.0));
```

It will print the following output:

    The value of pi is: 3.141592653589793
    The value of pi/2 is: 1.5707963267948966
    The value of pi/3 is: 1.0471975511965976
    The value of pi/4 is: 0.7853981633974483
    The value of pi/6 is: 0.5235987755982988
    The negative of pi/6 is: -0.5235987755982988
    The inversion of pi/3 is: -2.0943951023931957
    The sum of pi/6 and pi/3 is: 1.5707963267948966
    The difference of pi/3 and pi/2 is: -0.5235987755982989
    The sine of pi/6 is: 0.49999999999999994
    The cosine of pi/3 is: 0.5000000000000001
    The tangent of pi/2 is: Infinity
    The arctangent of 1.0 is: 0.7853981633974483

