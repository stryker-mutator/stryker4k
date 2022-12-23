By default Stryker4k is configured for projects using Gradle.

For projects using other build tools or for a more optimized Gradle experience, you can add a file called `stryker-conf.json` too your project root.
This file should look something like this:
```json
{
    "command": "./gradlew test --rerun-tasks"
}
```
`"./gradlew test --rerun-tasks"` is where your custom command your project uses for running your tests should go.

##Configuration options

### `command` [`string`]

**Config file:** `"command": "./gradlew test --rerun-tasks"`  
**Default value:** `"./gradlew test --rerun-tasks"`  
**Description:**  
With `command` you configure the command that should be used by Stryker4k to run your project's tests during mutation testing.
The default for this will use the Gradle wrapper to run the `test` task, and it's dependencies, for every generated mutation.

When configuring Stryker4k for other build tools, such as Maven, please make sure the given command does not cache it's results but reruns the tests for every mutation. 

#### Recommendation

Stryker4k can be sped up by about 25% in projects using Fradle if you use the following custom configuration.
1. Add the following task too your Gradle build script. (`build.gradle.kts`)
```kotlin
tasks.register<Test>("stryker") {
    outputs.upToDateWhen { false }
    failFast = true
    // your test task contents go here. For example:
    useJUnit()
}
```
2. Set the command value in your `stryker-conf.json` to: `./gradlew stryker`

This configuration wil cache the output of your build tasks while still rerunning your test task for every mutation.
