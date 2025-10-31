plugins {
    id("java")
}

group = "com.nubit.integration"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.springframework.boot:spring-boot-starter-integration:3.5.7")
    implementation("org.springframework.integration:spring-integration-test:6.5.3")
    implementation("org.springframework.integration:spring-integration:6.5.3")
    implementation("org.springframework.integration:spring-integration-jdbc:6.5.3")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("com.h2database:h2:2.1.214")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.20.0")
}

tasks.test {
    useJUnitPlatform()
}