package process.projectLoader

import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import models.ConfigLoader
import models.Configuration
import models.SourceFile
import process.testRunner.ConsoleReporter
import process.testRunner.HTMLReporter
import utility.FileUtility
import utility.LoggingUtility
import utility.PsiUtility
import java.io.IOException
import kotlin.system.exitProcess

object ProjectLoader {
    val logger = LoggingUtility()

    fun loadProject(path: String): List<SourceFile> {
        loadConfig(path)

        return loadKotlinFiles(path)
    }

    private fun loadConfig(path: String) {
        Configuration.sourcePath = path.replace("\\", "/")
        try {
            val confFileContent = FileUtility.readFile("$path/stryker-conf.json")
            val decodedContent = Json.decodeFromString<ConfigLoader>(confFileContent)
            Configuration.command = decodedContent.command
            Configuration.reporters = decodedContent.reporters.map { when(it) {
                "console" -> ConsoleReporter()
                "html" -> HTMLReporter()
                else -> throw IOException("Given reporter option: '$it' is not supported")
            } }
        } catch (e: IOException) {
            logger.info { "Using default configuration" }
            println(e)
        } catch (e: SerializationException) {
            logger.info { "Failed to load configuration file. Using default configuration" }
            println(e)
        }
    }

    private fun loadKotlinFiles(path: String): List<SourceFile> {
        val kotlinFiles = mutableListOf<SourceFile>()
        try {
            FileUtility.readDir(path).forEach {
                if (!it.contains(Regex("""src[\\/]test""")) && it.endsWith(".kt")) {
                    val psiFile = PsiUtility.createPsiFile(FileUtility.readFile(it))
                    kotlinFiles.add(SourceFile(it.substring(path.length), psiFile))
                }
            }
        } catch (e: IOException) {
            logger.info { "Unable to read project source files" }
            println(e)
            exitProcess(1)
        }

        return kotlinFiles
    }
}
