# IN HALTER PATTERN FACTORYPATTERN - Command and its CommandContext and CommandFactory

## Project: Proof of Concept for Command and its CommandContext and CommandFactory with Convention over Configuration Variants 

## Use it - define in your build.gradle file

`dependencies {`

`  // use at runtime`

`compile "in.halter.pattern:in.halter.factory-pattern-api:1.0"`

`compile "in.halter.pattern:in.halter.factory-pattern:1.0"`

`}`

## When you have a serial Sequence of Commands, each executed one after another ...	
 
you can use this Proof of Concept as a starting Point.

You can choose about Different Implementations of the CommandFactories, or write your own.

The CommandContext is ready to handle multiple Properties Groups, used by the CommandFactory or the CommandContext it self.

## Proof of Concept

Simple Challenge:

- Define a surroundings for Commands
- Build Command for Command to build a Command Library for a defined Businesscase
- Define your Conventions and minimize your Configuration

This Implementation is a small playground to do some Verification in Java Implementation without all the other Details Things in the Businesscase. 

## Convention

A **Command Id**

- is a string
- starts with a `CMD` followed by a `Number` followed by a `Letter` for the Implementation Language if you have more then one (i.E. Scala).
- **Example:** `CMD1234J` (Letter is J for Java)
