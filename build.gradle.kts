plugins {
    java
    alias(libs.plugins.spotless)
}

subprojects {
  apply(plugin = "com.diffplug.spotless")

  spotless {
    java {
      licenseHeaderFile(file("$rootDir/license/header.txt"))
      trimTrailingWhitespace()
      indentWithSpaces(2)
      endWithNewline()
    }
    kotlinGradle {
      trimTrailingWhitespace()
      indentWithSpaces(4)
      endWithNewline()
    }
  }
}

tasks {
    compileJava {
        dependsOn(spotlessApply)
    }
}