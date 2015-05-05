# CH CONST1 - Code Const

## Project: domain driven, reusable and immutable constant values for java programming language

## Use it - define in your build.gradle file

`dependencies {`

`  // use at runtime`

`compile "ch.const1:ch.const1-lang-ja8:1.0"`

`  // use in tests`

`  testCompile "ch.const1:ch.const1-lang-ja8:1.0"`

`}`

## When you write Unittests with many Data Values

you write many constant values

- Strings (with a Domain behind)
- Numbers (with a Domain behind)

for

- Setting
- Searching
- Validating

direct in your code.

## This is a approach to define reusable and immutable constant values as simple as possible.

First start small

- with immutable, domain driven values.

Later on

- with generated constant values from your Domain specific Database
- with generated constant values from your Domain specific REST API
- with search in your Domain specific test (Unittest, Integrationtest, Systemtest) code base for constant values.
- with search in your Domain specific productive code base for constant values.

combinated to

- Methodes that gives you real reusable domain driven constant values that you need.

gives you

- better test values
- better Integration in your IDE, like insert a method call.

#### Think about Test Data Combination - Data Driven Testing

What do you think? How many Data Combinations for example a Person can you define with

- 5 Prenames
- 5 Lastnames
- 5 Streets
- 5 Places

I think: Enough for Unit Tests, one key ist to have my domain specific values

#### Naming convention

Each **value identifier**

- starts with a `C_` followed by a `DOMAIN` followed by a `_` followed by a identifier.
- **Example:** `C_PRENAME_BEN` (DOMAIN is PRENAME)

Each **class identifier**

- starts with a `Const` followed by a `DOMAIN`.
- **Example:** `ConstPrename` (DOMAIN is Prename)
- is `public static final` (immutable)
- is in a `public final class`

Each **constant value**

- is a primitive type if possible (performance)

Each **class**

- is a `public final class`
- has a `private` constructor (no need to instantiate)
