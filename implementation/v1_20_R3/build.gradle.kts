val versionAdapterTarget = "1.20.4"

plugins {
  alias(libs.plugins.shadow)
  alias(libs.plugins.paperweight)
}

java {
  toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks {
  shadowJar {
    archiveBaseName.set(project.name)
    minimize()
    relocate("org.jetbrains.annotations", "site.dragonstudio.visual.libs.org.jetbrains.annotations")
  }
  build {
    dependsOn(shadowJar)
  }
}

dependencies {
  api(project(":ds-visual-api"))

  paperweightDevBundle("$versionAdapterTarget-R0.1-SNAPSHOT")
}
