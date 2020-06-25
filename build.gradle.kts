plugins {
    java
    application
    id("com.github.johnrengelman.shadow") version "5.2.0"
}

repositories {
    mavenCentral()
}

val javaFXModules = listOf (
        "base",
        "controls",
        "fxml",
        "graphics",
	    "media"
)

val supportedPlatforms = listOf(
        "linux",
        "mac",
        "win"
)

dependencies {
    //JavaFX modules
    for(platform in supportedPlatforms) {
        for(module in javaFXModules) {
            implementation("org.openjfx:javafx-$module:13:$platform")
        }
    }

    implementation("org.apache.commons:commons-lang3:3.8.1")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.11.+")


    //TEST DEPENDENCIES
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.5.2")
    testImplementation("org.mockito:mockito-core:3.3.3")
    testImplementation("org.mockito:mockito-junit-jupiter:3.3.3")
    testImplementation("org.testfx:testfx-junit5:4.0.16-alpha")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.5.2")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClassName = "it.dpg.maingame.launcher.Launcher"
}
