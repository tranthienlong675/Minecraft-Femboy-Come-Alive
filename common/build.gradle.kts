architectury {
    common(
        rootProject.property("enabled_platforms")
            .toString()
            .split(",")
    )
}

repositories {
    maven {
        name = "Conczin"
        url = uri("https://maven.conczin.net/Artifacts/")
    }
}

dependencies {
    modImplementation(
        "net.fabricmc:fabric-loader:${rootProject.property("fabric_loader_version")}"
    )

    modImplementation(
        "dev.architectury:architectury:${rootProject.property("architectury_api_version")}"
    )

    implementation("net.conczin.mca:mca-common:7.7.33+1.21.1")
}