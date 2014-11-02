# Java Primitive Types

## In a Nut Shell
This project contains a set of Java primitive type classes. Each class follows the same immutability
conventions that the native Java primitive types support. They also provide a default implementation
of the canonical `Object` class methods.

## Highlighted Components
The following highlights the new primitive types that this project provides:

 * *Angle* - representing the mathematical concept of an angle in radians in the range (-pi..pi]
 * *Binary* - an extension of `byte[]` that adds support for the `craterdog.core.Sequential` and
`craterdog.core.Composite` interfaces
 * *Probability* - representing a mathematical probability in the range [0.0..1.0)
 * *Tag* - a unique identifier of any length displayed in base 32 format
 * *Text* - an extension of the `java.lang.String` class that adds support for the `craterdog.core.Sequential`
interface

## Quick Links
For more detail on this project click on the following links:

 * [javadocs](http://craterdog.github.io/java-primitive-types/3.1/index.html)
 * [wiki](https://github.com/craterdog/java-primitive-types/wiki)
 * [release notes](https://github.com/craterdog/java-primitive-types/wiki/Release-Notes)
 * [website](http://craterdog.com)

## Getting Started
To get started using these classes, include the following dependency in your maven pom.xml file:

```xml
    <dependency>
        <groupId>com.craterdog</groupId>
        <artifactId>java-primitive-types</artifactId>
        <version>3.1</version>
    </dependency>
```

The source code, javadocs and jar file artifacts for this project are available from the
*Maven Central Repository*. If your project doesn't currently use maven and you would like to,
click [here](https://github.com/craterdog/maven-parent-poms) to get started down that path quickly.

