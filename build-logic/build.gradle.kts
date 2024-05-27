plugins {
	`kotlin-dsl`
	alias(libs.plugins.indra)
    alias(libs.plugins.spotless)
}

repositories {
	gradlePluginPortal()
}

indra {
  javaVersions {
    target(8)
    minimumToolchain(8)
  }
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
