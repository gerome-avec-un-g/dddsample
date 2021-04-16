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

The glue should only be the shared package and this business domain package to avoid coupling with other business
domains.

### fr.geromeavecung.exposition.presentation.cucumber.books.repositories

Contains the in memory implementations of the business domain repositories

### fr.geromeavecung.exposition.presentation.cucumber.books.steps

Contains the steps for your features.

You should have one Step file for each of your feature. 1 feature = 1 Step file = 1 Orchestration Service

You can have some common Steps that are shared by multiple features, often for setting the user.

The name of the public methods for steps should be snake_case prefixed by either given_, when_ or then_. Snake_case for
test methods makes it easy to identify them when you are browsing caller hierarchy. The prefix make it easier to lookup
when browsing file structure;

Private methods should follow standard Java naming conventions.


Only one @Given ?

You should have only one @When, which is the call the orchestration service corresponding to your feature

```
@When("the logged-in user does the action")
public void when_the_logged_in_user_does_the_action() {
    sharedState.setActualException(null);
    try {
        result = actionPresentationService.action();
    } catch (Exception exception) {
        sharedState.setActualException(exception);
    }
}
```
It is important the reset the exception and catch the one throw by your specific service call 
to avoid getting exception from previous tests. 

@Then corresponds to the verification of the object returned by the presentation service. If the object is
complex, you can break it down with multiple @Then

# Features

# Documentation

The documentation is generated with Cukedoctor maven plugin in exposition/presentation/target/documents.

The plugin is executed once for each business domain :
```
<!-- do once for each business domain -->
<execution>
    <id>business-domain</id>
    <goals>
        <goal>execute</goal>
    </goals>
    <phase>verify</phase>
    <configuration>
        <!-- you can't have "." or ":" in file name  see also <maven.build.timestamp.format> -->
        <outputFileName>documentation-business-domain-${maven.build.timestamp}</outputFileName>
        <featuresDir>target/business-domain.json</featuresDir>
        <documentTitle>Business Domain</documentTitle>
    </configuration>
</execution>
```
"featuresDir" is the name of the json file configured in the class BusinessDomainRunnerTest.java @CucumberOptions.plugin


To be able to **use ${maven.build.timestamp} in the file name, it should be properly formatted
```
<properties>
    ...
    <!-- file name friendly timestamp format -->
    <maven.build.timestamp.format>yyyy-MM-dd-HHmm</maven.build.timestamp.format>
    ...
</properties>
```

I18N is available in src/main/resources/cukedoctor.properties
/!\ TODO ? https://github.com/rmpestano/cukedoctor#641-reading-features

You can set a custom introduction with src/main/resources/cukedoctor-intro.adoc