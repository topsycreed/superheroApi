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
    testImplementation("io.rest-assured:rest-assured:5.4.0")
    testImplementation("org.hamcrest:hamcrest-all:1.3")
    testImplementation("com.fasterxml.jackson.core:jackson-annotations:2.16.1")
    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.16.1")
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
    testCompileOnly("org.projectlombok:lombok:1.18.30")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.30")
//    testImplementation("org.projectlombok:lombok:1.18.28")
//    annotationProcessor("org.projectlombok:lombok:1.18.28")
////    testAnnotationProcessor("org.projectlombok:lombok")
}

tasks.test {
    useJUnitPlatform()
}