val versionAdapterTarget = "1.17.1"

plugins {
    alias(libs.plugins.shadow)
  alias(libs.plugins.paperweight)
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
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

    paperweight.paperDevBundle("$versionAdapterTarget-R0.1-SNAPSHOT")
  pluginRemapper("net.fabricmc:tiny-remapper:0.10.3:fat")
}
