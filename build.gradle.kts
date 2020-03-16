import kr.entree.spigradle.kotlin.spigot

plugins {
    java
    id("kr.entree.spigradle") version "1.2.1"
    id("com.github.johnrengelman.shadow") version "5.2.0"
}

group = "kr.entree"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    val kotlinVersion = "1.3.70"
    compileOnly(spigot())
    implementation(kotlin("script-runtime", kotlinVersion))
    implementation(kotlin("script-util", kotlinVersion))
    implementation(kotlin("compiler-embeddable", kotlinVersion))
    implementation(kotlin("scripting-compiler-embeddable", kotlinVersion))
    testCompile("junit", "junit", "4.12")
}

spigot {
    commands {
        create("kotlinscript") {
            aliases = listOf("kscript", "ks")
        }
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks {
    jar {
        dependsOn(shadowJar)
    }
}