plugins {
    alias(libs.plugins.shadow)
}

tasks {
    processResources {
        filesMatching("plugin.yml") {
            expand("version" to project.version)
        }
    }
    shadowJar {
        archiveBaseName.set(rootProject.name)
        archiveClassifier.set("Plugin")
        minimize()

        relocate("org.jetbrains.annotations", "site.dragonstudio.visual.libs.org.jetbrains.annotations")
    }
    build {
        dependsOn(shadowJar)
    }
}

dependencies {
    api(project(":ds-visual-api"))
    api(project(":ds-visual-v1_8_R3"))

    compileOnly(libs.platform)
}
