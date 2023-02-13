plugins {
    kotlin("jvm") version "1.8.0"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    maven("https://maven.pkg.github.com/tuProlog/2p-kt")
    mavenCentral()
}

val tuPrologVersion: String = "0.14.11"

dependencies {
    implementation("it.unibo.tuprolog:repl-jvm:$tuPrologVersion")
    implementation("it.unibo.tuprolog:dsl-solve-jvm:$tuPrologVersion")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}