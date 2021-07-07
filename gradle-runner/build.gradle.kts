import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "io.stryker-mutator"
version = "0.1.0"

plugins {
    kotlin("jvm")
    `maven-publish`
    `java-gradle-plugin`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.stryker-mutator:stryker4k-core:0.1.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.4.20")
    testImplementation(kotlin("test-junit"))
    testImplementation("io.mockk:mockk:1.10.6")
    implementation("org.jetbrains.kotlin:kotlin-compiler-embeddable:1.4.20")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")
    implementation("io.stryker-mutator:mutation-testing-elements:1.7.0")
    implementation("io.stryker-mutator:mutation-testing-metrics-circe_2.13:1.7.0")
    implementation(gradleApi())
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

gradlePlugin {
    plugins {
        create("stryker4k") {
            id = "io.stryker-mutator.stryker4k"
            displayName = "stryker4k"
            description = "A mutation testing framework for Kotlin."
            implementationClass = "Stryker4kGradlePlugin"
        }
    }
}
