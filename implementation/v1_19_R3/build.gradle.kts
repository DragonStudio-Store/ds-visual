val versionAdapterTarget = "1.19.4"

plugins {
  alias(libs.plugins.paperweight)
}

indra {
  javaVersions {
    target(17)
    minimumToolchain(17)
  }
}

dependencies {
  api(project(":ds-visual-api"))

  paperweight.paperDevBundle("$versionAdapterTarget-R0.1-SNAPSHOT")
}
