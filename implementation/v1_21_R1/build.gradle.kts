val versionAdapterTarget = "1.21"

plugins {
  alias(libs.plugins.paperweight)
}

indra {
  javaVersions {
    target(21)
    minimumToolchain(21)
  }
}

dependencies {
  api(project(":ds-visual-api"))

  paperweight.paperDevBundle("$versionAdapterTarget-R0.1-SNAPSHOT")
}
