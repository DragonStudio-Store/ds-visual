val versionAdapterTarget = "1.20.1"

plugins {
    alias(libs.plugins.shadow)
  alias(libs.plugins.paperweight)
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks {
    shadowJar {
        archiveFileName.set(rootProject.name)
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

    paperweight.paperDevBundle("$versionAdapterTarget-R0.1-SNAPSHOT")
}
