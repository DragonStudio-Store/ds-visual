tasks {
  processResources {
    filesMatching("plugin.yml") {
      expand("version" to project.version)
    }
  }
}

dependencies {
  api(project(":ds-visual-api"))
  api(project(":ds-visual-v1_8_R3"))

  compileOnly(libs.platform)
}
