plugins {
    java
    application
}

repositories {
    mavenCentral()
}

val javaFXModules = listOf (
        "base",
        "controls",
        "fxml",
        "graphics"
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

    //TEST DEPENDENCIES
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.5.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.5.2")
}