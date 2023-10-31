# Hello-Multi

This project can be used as a starting point to create your own multi-module Vaadin application with Spring Boot.
It contains all the necessary configuration and some placeholder files to get you started.

You can download a Vaadin app starter with additional configurations at [start.vaadin.com](https://start.vaadin.com) 
(a visual tool for quickly generating Vaadin web apps that you can download and open in your IDE).

## Running the application
The project is a standard Maven project. To run it from the command line,
type `mvn`, then open http://localhost:8080 in your browser.

You can also import the project to your IDE of choice as you would with any
Maven project. Read more on [how to set up a development environment for
Vaadin projects](https://vaadin.com/docs/latest/guide/install) (Windows, Linux, macOS).

## Deploying to Production
To create a production build, call `mvn clean package -Pproduction`.
This will build a JAR file with all the dependencies and front-end resources,
ready to be deployed. The file can be found in the `target` folder after the build completes.

Once the JAR file is built, you can run it using
`java -jar target/hello-multi-1.0-SNAPSHOT.jar` (NOTE, replace 
`hello-multi-1.0-SNAPSHOT.jar` with the name of your jar).

## Project Structure

### Modules and Packages

`hello-multi`
  : The parent (aggregator) module for the project. Although there is no Java source in this module, you can think of this module as defining the root package for the project as `org.joelpop.hellomulti`.

- `hello-multi-model`
  : The UI data model module. It is where data structures used by the UI are defined via records, classes, and enums. This module is a dependency of the `hello-multi-service` and `hello-multi-mockservice` (and transitively of the `hello-multi-ui`) modules. Java source in this module should be placed in the `org.joelpop.hellomulti.model` package.

- `hello-multi-service`
  : The interface methods called by the UI. Java source in this module should be placed in the `org.joelpop.hellomulti.service` package.

- `hello-multi-mockservice`
  : The implementation of the service methods. The module can use REST, JPA, mocks, etc. to implement the service methods and should be appropriately named. This pattern allows you to interchange or replace one implementation with another without disturbing the service interface or UI code. Note that although this package has access to the service and model modules, it has no access to the UI module. Any "communication" to the UI should be done via throwing exceptions. Java source in this module should be placed in the `org.joelpop.hellomulti.mockservice` package.

- `hello-multi-ui`
  : The Vaadin user interface module. All Vaadin code belongs in this module and nowhere else. Contains both the components and their event handling. Note that although this package has access to the service and model modules, it has no access to the service implementation module. Any "communication" from the service implementation should be done via catching exceptions thrown by the service implementation module. With the exception of the `Application` class (see below), Java source in this module should be placed in the `org.joelpop.hellomulti.ui` package.

### Example Source

- `POM.xml` Put your project-wide properties, dependency management, plugin management, etc. in the POM in the `hello-multi` module. Put your module-specific dependencies and build rules in the POM of the corresponding module.

- `src/main/java` contains Java sources.

  - `Greeting` (record) Contains the data returned by the service for use by the UI. `org.joelpop.hellomulti.model.record`

  - `GreetingService` (interface) Contains the service interface methods called by the UI. `org.joelpop.hellomulti.model.service`

  - `MockGreetingService` (class) Contains the implementations of methods defined in the service module. `org.joelpop.hellomulti.model.mockservice`

  - `HelloView` (class) An example Vaadin view that calls the greeting service each time its button is clicked. `org.joelpop.hellomulti.ui.view`

  - `Application` (class) The entry point to the application. `org.joelpop.hellomulti`

- `src/main/resources` contains configuration files and static resources.

- `frontend` contains client-side dependencies and resource files.

## Useful links

- Read the documentation at [vaadin.com/docs](https://vaadin.com/docs).
- Follow the tutorials at [vaadin.com/tutorials](https://vaadin.com/tutorials).
- Watch training videos and get certified at [vaadin.com/learn/training]( https://vaadin.com/learn/training).
- Create new projects at [start.vaadin.com](https://start.vaadin.com/).
- Search UI components and their usage examples at [vaadin.com/components](https://vaadin.com/components).
- Find a collection of solutions to common use cases in [Vaadin Cookbook](https://cookbook.vaadin.com/).
- Find Add-ons at [vaadin.com/directory](https://vaadin.com/directory).
- Ask questions on [Stack Overflow](https://stackoverflow.com/questions/tagged/vaadin) or join our [Discord channel](https://discord.gg/MYFq5RTbBn).
- Report issues, create pull requests in [GitHub](https://github.com/vaadin/).
