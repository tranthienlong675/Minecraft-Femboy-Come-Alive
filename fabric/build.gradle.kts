import org.jetbrains.kotlin.gradle.utils.extendsFrom

plugins {
    id("com.gradleup.shadow")
}

repositories {
    maven {
        name = "Conczin"
        url = uri("https://maven.conczin.net/Artifacts/")
    }
}

architectury {
    platformSetupLoomIde()
    fabric()
}

val common: Configuration by configurations.creating {
    isCanBeResolved = true
    isCanBeConsumed = false
}
val shadowBundle: Configuration by configurations.creating {
    isCanBeResolved = true
    isCanBeConsumed = false
}
val developmentFabric: Configuration by configurations.getting

configurations {
    compileOnly.configure { extendsFrom(common) }
    runtimeOnly.configure { extendsFrom(common) }
    developmentFabric.extendsFrom(common)
}

dependencies {
    modImplementation("net.fabricmc:fabric-loader:${rootProject.property("fabric_loader_version").toString()}")

    // Fabric API. This is technically optional, but you probably want it anyway.
    modImplementation("net.fabricmc.fabric-api:fabric-api:${rootProject.property("fabric_api_version").toString()}")

    // Architectury API. This is optional, and you can comment it out if you don't need it.
    modImplementation("dev.architectury:architectury-fabric:${rootProject.property("architectury_api_version").toString()}")

    common(project(":common", "namedElements")) {
        isTransitive = false
    }
    shadowBundle(project(":common", "transformProductionFabric")){
        isTransitive = false
    }

    //modImplementation("net.fabricmc:fabric-language-kotlin:${rootProject.property("fabric_language_kotlin_version")}")

    //modImplementation("maven.modrinth:minecraft-comes-alive-reborn:${rootProject.property("mca_version")}")
    //implementation("net.conczin.mca:mca-fabric:7.7.33+1.21.1")
    implementation("net.conczin.mca:mca-common:7.7.33+1.21.1")
}

tasks.processResources {
    inputs.property("version", project.version)

    inputs.property(
        "mod_id",
        rootProject.property("mod_id")
    )

    inputs.property(
        "mod_name",
        rootProject.property("mod_name")
    )

    inputs.property(
        "fabric_loader_version",
        rootProject.property("fabric_loader_version")
    )

    inputs.property(
        "minecraft_version",
        rootProject.property("minecraft_version")
    )

    inputs.property(
        "architectury",
        rootProject.property("architectury_api_version")
    )

    filesMatching("fabric.mod.json") {
        expand(
            mutableMapOf(
                "version" to inputs.properties["version"],
                "mod_id" to rootProject.property("mod_id"),
                "mod_name" to rootProject.property("mod_name"),
                "architectury" to rootProject.property("architectury_api_version"),
                "minecraft_version" to rootProject.property("minecraft_version"),
                "fabric_loader_version" to rootProject.property("fabric_loader_version"),
            )
        )
    }
}

tasks.shadowJar {
    configurations = listOf(project.configurations.named("shadowBundle").get())
    archiveBaseName.set("${rootProject.property("mod_id")}")
    archiveVersion.set("${rootProject.property("mod_version")}-fabric")
    archiveClassifier.set("dev-shadow")
}

tasks.remapJar {
    inputFile.set(tasks.shadowJar.flatMap { it.archiveFile })
    archiveBaseName.set("${rootProject.property("mod_id")}")
    archiveVersion.set("${rootProject.property("mod_version")}-fabric")
    archiveClassifier.set("")
}