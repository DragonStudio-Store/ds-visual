val versionAdapterTarget = "1.11.2"

plugins {
    alias(libs.plugins.shadow)
}

tasks {
    shadowJar {
        archiveBaseName.set(rootProject.name)
        archiveClassifier.set("Adapter-$versionAdapterTarget")
        minimize()

        relocate("org.jetbrains.annotations", "site.dragonstudio.visual.libs.org.jetbrains.annotations")
    }
    build {
        dependsOn(shadowJar)
    }
}

dependencies {
    api(project(":ds-visual-api"))

    compileOnly("org.spigotmc:spigot:$versionAdapterTarget-R0.1-SNAPSHOT")
}
