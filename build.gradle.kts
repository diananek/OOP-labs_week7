plugins {
    id("java")
    application
}

group = "org"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainModule.set("org.example")
    mainClass.set("org.example.App")
}

dependencies {
    implementation("org.jfree:jfreechart:1.5.3")
    implementation("org.apache.commons:commons-math3:3.6.1")
    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
}


tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    manifest {
        attributes["Main-Class"] = "org.example.App"
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    val sourcesMain = sourceSets.main.get()
    from(sourcesMain.output)
}

