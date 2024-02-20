plugins {
    id("java")
}

group = "org.marvel"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("io.rest-assured:rest-assured:5.4.0")
    testImplementation("org.hamcrest:hamcrest-all:1.3")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.16.1")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.1")
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
    testCompileOnly("org.projectlombok:lombok:1.18.30")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.30")
    testImplementation("io.qameta.allure:allure-junit5:2.25.0")
    implementation("org.apache.httpcomponents:httpclient:4.5.14")
    testImplementation("org.assertj:assertj-core:3.25.3")
}

tasks.test {
    useJUnitPlatform()
}