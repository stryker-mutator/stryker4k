package models

import kotlinx.serialization.Serializable
import process.testRunner.ConsoleReporter
import process.testRunner.Reporter

@Serializable
data class ConfigLoader(
    val reporters: Array<String> = arrayOf("console")
    val command: String = "./gradlew test --rerun-tasks",
)

object Configuration {
    var sourcePath: String = ""
    var command: String = "./gradlew test --rerun-tasks"
    var reporters: List<Reporter> = listOf(ConsoleReporter())
}
