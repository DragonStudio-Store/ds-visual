plugins {
    alias(libs.plugins.shadow)
}

tasks {
    shadowJar {
        archiveBaseName.set(rootProject.name)
        archiveClassifier.set("API")
        minimize()

        relocate("org.jetbrains.annotations", "site.dragonstudio.visual.libs.org.jetbrains.annotations")
    }
    build {
        dependsOn(shadowJar)
    }
}

dependencies {
    api(project(":ds-visual-common"))

    compileOnly(libs.platform)
}
