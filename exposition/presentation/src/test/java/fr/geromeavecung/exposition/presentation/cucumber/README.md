Cucumber

# Technical files and test automation

## Shared package

### fr.geromeavecung.exposition.presentation.cucumber.shared.CucumberConfiguration.java

This is the main configuration for Cucumber. It references the configurations for shared state and shared repositories
implementations. It also references the configurations of each specific functional domains, which should be added when
creating a new functional domain.

### fr.geromeavecung.exposition.presentation.cucumber.shared.SharedState.java

Contains common data that is used by different Cucumber steps, like the identifier of the logged-in user doing the
actions, the current exception...

### fr.geromeavecung.exposition.presentation.cucumber.shared.repositories.XXXInMemory.java

Some in memory implementations of repositories that are shared by multiple functional domains (Identifiers,
Timestamps...)
IdentifiersInMemory and TimestampsInMemory are here to get rid of the randomness of UUID.randomUUID() and
LocalDateTime.now(). They always return a fixed value that can be changed with the appropriate steps in SharedSteps.

### fr.geromeavecung.exposition.presentation.cucumber.shared.SharedSteps.java

Steps for all shared in memory implementation of repositories and exception verification. Also contains some
configuration to manage null/empty strings for DataTables.

## Functional Domain Package

You have one package for each functional domain.

### fr.geromeavecung.exposition.presentation.cucumber.businessdomain.BusinessDomainRunnerTest.java

This is the configuration for launching tests of the functional domain.

You can run a single feature by changing features to:

```
features = {"src/test/resources/features/THE_FEATURE_I_WANT_TO_RUN.feature"},
``` 

You can also run specific tests for debugging purpose by changing tags to

```
tags = "@only"
```

and adding the tag to the test you want to debug.

```
plugin = {"json:target/businessdomain.json"},
```

is used to generate one document per business domain

### fr.geromeavecung.exposition.presentation.cucumber.books.repositories

Contains the in memory implementations of the business domain repositories

### fr.geromeavecung.exposition.presentation.cucumber.books.steps

Contains the steps for your features.

You should have one Step file for each of your feature. 
You can have some common Steps that are shared by multiple features, often for setting the user.

The name of the public methods for steps should be snake_case prefixed by either given_, when_ or then_. 
Snake_case for test methods makes it easy to identify them when you are browsing caller hierarchy.
The prefix make it easier to lookup when browsing file structure;

Private methods should follow standard Java naming conventions. 

# Features

# Documentation
