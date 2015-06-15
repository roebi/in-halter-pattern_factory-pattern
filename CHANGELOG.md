# patterns factorypattern - Changelog

## 1.0

### New Features

API of Command and its CommandContext and CommandFactory Classes:

Package `in.halter.patterns.factorypattern.api`

- `Command`
- `CommandContext`
- `CommandFactory`

with a Context and some Factories Implementation in

Package `in.halter.patterns.factorypattern.impl`

- `CommandContextImpl`
- `CommandFactoryWithConfigurationImpl`
- `CommandFactoryWithReflectionImpl`

and some Command Implementation in

Package `in.halter.patterns.factorypattern.impl.command.generic`

- `WriteToStepPageGeneric`

and some Command Implementation in

Package `in.halter.patterns.factorypattern.impl.command.java`

- `CMD1234J`
- `CMD5678J`

### Deployed to bintray maven repository

- url : `http://dl.bintray.com/roebi/maven/`
- api Classes and impl Classes in own jars separated

- group 4 api : `in.halter.pattern-api`
- package 4 api : `in.halter.factory-pattern-api`
- version 4 api : `1.0`

- group 4 impl : `in.halter.pattern-impl`
- package 4 impl : `in.halter.factory-pattern-impl`
- version 4 impl: `1.0`

### Available on bintray jcenter
