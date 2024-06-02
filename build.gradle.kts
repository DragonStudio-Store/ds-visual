plugins {
    java
    alias(libs.plugins.spotless)
	alias(libs.plugins.shadow)
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
    shadowJar {
        archiveFileName.set("${rootProject.name}")
		
		relocate("org.jetbrains.annotations", "site.dragonstudio.visual.libs.org.jetbrains.annotations")
    }
}