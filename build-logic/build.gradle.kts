plugins {
	`kotlin-dsl`
    alias(libs.plugins.spotless)
}

repositories {
	gradlePluginPortal()
}

spotless {
  java {
    licenseHeaderFile(file("license/header.txt"))
    removeUnusedImports()
    trimTrailingWhitespace()
    endWithNewline()
  }
  kotlinGradle {
    trimTrailingWhitespace()
    endWithNewline()
  }
}

tasks {
  compileJava {
    dependsOn(spotlessApply)
  }
}
