import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":core"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.7.21")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    testImplementation(kotlin("test-junit"))
    testImplementation("io.mockk:mockk:1.13.2")
}

tasks.test {
    useJUnit()
}

tasks.register<Test>("stryker") {
    outputs.upToDateWhen { false }
    failFast = true
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}
