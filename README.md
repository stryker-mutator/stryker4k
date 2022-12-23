**PLEASE NOTE: This project is currently still being setup and the installation instructions found below will not work yet. With the 0.1.0 release all will work as described below.**

![stryker-80x80](https://user-images.githubusercontent.com/10114577/59962899-d26b8d00-94eb-11e9-8e31-18b3d8d96fd3.png)

# Stryker4k

_Professor X: For someone who hates mutants... you certainly keep some strange company._  
_William Stryker: Oh, they serve their purpose... as long as they can be controlled._

## Introduction

Stryker4k offers you mutation testing for your Kotlin projects. It allows you to test your tests by temporarily inserting changes to your code. Your tests should be able to detect these changes.

Detailed documentation on Mutation testing and Stryker can be found on [the Stryker Mutator website](https://stryker-mutator.io/).

## Getting Started

To get started using Stryker4k on your Kotlin projects using Gradle, please follow the steps bellow.
If your Kotlin project does not use Gradle, please read the alternative installation steps on [our Getting started page](https://stryker-mutator.io/docs/stryker4k/Getting-started).

### 1 Install

Please install on your project by adding Stryker4k to your build tool configuration file. With Gradle that means you add this too your `build.gradle.kts`
```kotlin
plugins {
    // Your project's other plugins go here
    id("io.stryker-mutator.stryker4k") version stryker4kVersion
}
```

### 2 Configure

By default Stryker4k is configured for projects using Gradle.

For projects using other build tools or for a more optimized Gradle experience, you can add a file called `stryker-conf.json` too your project root.
This file should look something like this:
```json
{
    "command": "./gradlew test --rerun-tasks"
}
```
`"gradlew test --rerun-tasks"` is where your custom command your project uses for running your tests should go.

For more information on this topic please visit [our configuration page](https://stryker-mutator.io/docs/stryker4k/Configuration).

### 3 Run

Please run Stryker4k by running `command` in your projects root folder.
