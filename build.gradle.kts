import org.gradle.api.plugins.JavaPluginExtension

plugins {
    id("java") 
    id("org.springframework.boot") version "3.3.3" apply false
    id("io.spring.dependency-management") version "1.1.6" apply false
}

allprojects {
    group = "com.example"
    version = "0.0.1-SNAPSHOT"
}

subprojects {
    repositories {
        mavenCentral()
    }

    pluginManager.withPlugin("java") {
        extensions.configure(JavaPluginExtension::class) {
            toolchain.languageVersion.set(JavaLanguageVersion.of(21))
        }
    }
}

