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

- url : `http://dl.bintray.com/content/roebi/halter/factorypattern`
- group : `in.halter.patterns.factorypattern`
- package : `factorypattern`
- version : `1.0`
- api Classes in own jar separated

### Available on bintray jcenter
