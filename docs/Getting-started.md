Below you will find steps to get you started with using Stryker4k. The simplest way to use Stryker4k is as a Gradle plugin, but below you wil also find steps for projects using other build tools.

---

# Gradle installation

## 1 Install

Please install Stryker4k by adding the Stryker4k Gradle plugin to your Gradle build script. If you are using the Gradle Kotlin DSL this will be `build.gradle.kts`. Stryker can be added like this:
```kotlin
plugins {
    // Your project's other plugins go here
    id("io.stryker-mutator.stryker4k") version stryker4kVersion
}
```
Please replace `stryker4kVersion` with your desired Stryker4k version number.


## 2 Configure

By default Stryker4k is configured for projects using Gradle.

For projects using other build tools or for a more optimized Gradle experience, you can add a file called `stryker-conf.json` too your project root.
This file should look something like this:
```json
{
    "command": "./gradlew test --rerun-tasks"
}
```
`"./gradlew test --rerun-tasks"` is where your custom command your project uses for running your tests should go.

For more information on this topic please visit [our configuration page](https://stryker-mutator.io/docs/stryker4k/Configuration).

## 3 Run

Please run Stryker4k by running `./gradlew stryker4k` in your projects root folder.

---

# Non-Gradle installation

## 1 Install

To use Stryker4k with Kotlin projects that do not use Gradle as their build tool. Please first download the Stryker4k sourcecode from https://github.com/stryker-mutator/stryker4k.git.

When you've downloaded the sourcecode, use the `./gradlew build command-runner` command to build the Stryker4k command-runner subproject.

The build will product a JAR file in the `build\libs` folder. We will use this JAR to run Stryker4k on your project.

## 2 Configure

Inside your own project, you will need to configure Stryker4k to run your projects tests. For this you will need to create a file called `stryker-conf.json` in the root of your project. In the file you will need to specify the command Stryker4k will use to run your tests.

Important to note here is that you don't want your build tool to only run the tests once and then cache the test results, because the sourcecode to test hasn't been changed.

When using the Maven build tool, your `stryker-conf.json` might look something like this:
```json
{
    "command": "mvn clean test"
}
```

For more information on this topic please visit [our configuration page](https://stryker-mutator.io/docs/stryker4k/Configuration).

## 3 Run

To use Stryker4k on your project, please run the JAR file generated in step 1 in your projects root folder.
