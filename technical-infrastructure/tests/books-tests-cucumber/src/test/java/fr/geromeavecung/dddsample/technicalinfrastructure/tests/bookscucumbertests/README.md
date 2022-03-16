#How to use Cucumber

## Technical files and test automation

### fr.geromeavecung.exposition.presentation.cucumber.shared

#### CucumberConfiguration.java

This is the main configuration for Cucumber. It references the shared configuration and the configurations of each specific functional domains, which should be added when creating a new functional domain.

#### SharedConfiguration

The configuration for shared repositories based on @ComponentScan

#### SharedState.java

Contains common data that is set by the functional domain steps, like the identifier of the logged-in user doing the actions or the current exception.

#### SharedSteps.java

Steps for all shared in-memory implementation of repositories, step for exception verification and configuration to manage empty strings for Cucumber tables.

#### repositories.XXXInMemory.java

Some in memory implementations of repositories that are shared by multiple functional domains. IdentifiersInMemory and TimestampsInMemory are here to get rid of the randomness of UUID.randomUUID() and ZonedDateTime.now(). They always return a fixed value that can be changed with the appropriate steps in SharedSteps.

### Functional Domain Package

You have one package for each functional domain.

#### fr.geromeavecung.exposition.presentation.cucumber.businessdomain.BusinessDomainRunnerTest.java

This is the configuration for launching tests of the functional domain.

You can run a single feature by changing features to:

```
features = {"src/test/resources/features/THE_FEATURE_I_WANT_TO_RUN.feature"},
``` 

You can also run specific tests for debugging purpose by changing tags to the following and adding the tag to the test you want to debug.

```
tags = "@only"
```

Plugin is used to generate one document per business domain :

```
plugin = {"json:target/businessdomain.json"},
```

The glue should only be the shared package and the current business domain package to avoid coupling with other business domains.

#### fr.geromeavecung.exposition.presentation.cucumber.books.repositories

Contains the in-memory implementations of the business domain repositories

#### fr.geromeavecung.exposition.presentation.cucumber.books.steps

Contains the steps for your features.

You should have one step file for each of your feature. 1 feature = 1 step file = 1 Orchestration Service

You can have some common Steps that are shared by multiple features, often for setting the user.

The name of the public methods for steps should be snake_case prefixed by either given_, when_ or then_. Snake_case for test methods makes it easy to identify them when you are browsing caller hierarchy. The prefix make it easier to lookup when browsing file structure;

Private methods should follow standard Java naming conventions.

##### Given
These steps are used to set-up data :
* the logged-in user doing the action
* the data the user is sending. It is one object, but if it has a lot of sub-objects and/collections, you can split in multiple steps
* the data the orchestration will interact with

##### When
You should have only one @When, which is the call the orchestration service corresponding to your feature

```
@When("the logged-in user does the action")
public void when_the_logged_in_user_does_the_action() {
    authorsSharedState.setActualException(null);
    try {
        result = actionPresentationService.action();
    } catch (Exception exception) {
        authorsSharedState.setActualException(exception);
    }
}
```

It is **important** to reset the exception and catch the one throw by your specific service call to avoid getting exception from previous tests.

##### Then
@Then corresponds to the verification of the object returned by the presentation service. If the object is complex, you can break it down with multiple @Then that are called one after the other, it should more or less match the presentation object return : one then per sub-object. Try to avoid multiple methods the check the same data but with different value that are just expressed in different natural expressions, use enum values instead (ie : "then the field is visible" and "then the field is not visible" should probably switch to "then the field is visible/hidden). 
It is **important** to check that no exception is returned by the @When, see SharedSteps.

## Features

## Documentation

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

To be able to use ${maven.build.timestamp} in the file name, it should be properly formatted because some characters are not authorized (".", ":"...):

```
<properties>
    ...
    <!-- file name friendly timestamp format -->
    <maven.build.timestamp.format>yyyy-MM-dd-HHmm</maven.build.timestamp.format>
    ...
</properties>
```

I18N is available in src/main/resources/cukedoctor.properties 
TODO https://github.com/rmpestano/cukedoctor#641-reading-features

You can set a custom introduction with src/main/resources/cukedoctor-intro.adoc but it will be the same for all documents. If you want a generic introduction to a business domain you can create an introductory feature with step-less scenarios