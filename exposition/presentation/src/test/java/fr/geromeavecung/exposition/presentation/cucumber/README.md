Cucumber

# Technical files and test automation

## Shared package

### CucumberConfiguration

This is the main configuration for Cucumber. 
It references the configurations for shared state and shared repositories implementations. 
It also references the configurations of each specific functional domains, which should be added when 
adding a new functional domain.

### SharedState

Contains common data that is used by different cucumber steps, like the identifier of the logged-in user doing the actions, the
current exception...

### XXXInMemory
Some in memory implementation of repositories that are shared by multiple functional domains (Identifiers, Timestamps...)
IdentifiersInMemory and TimestampsInMemory are here to get rid of the randomness of UUID.randomUUID() and 
LocalDateTime.now() they always return the same value that can be changed with the appropriate steps in SharedSteps.

### SharedSteps

Steps for all shared in memory implementation of repositories and exception verification.
Also contains some configuration to manage null/empty strings for DataTables.

## Functional Domain Package

You have one package for each functional domain.

### XXXRunnerTest

The configuration for launching tests of the functional domain

run single feature: features = {"src/test/resources/features/add_books.feature"}, run specific tests to debug: tags = "
@only"

# Features

# Documentation
