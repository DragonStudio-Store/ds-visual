plugins {
  `java-library`
  `maven-publish`
  alias(libs.plugins.spotless)
  alias(libs.plugins.indra)
  alias(libs.plugins.shadow)
}

subprojects {
  apply(plugin = "java-library")
  apply(plugin = "maven-publish")
  apply(plugin = "com.diffplug.spotless")
  apply(plugin = "net.kyori.indra")
  apply(plugin = "io.github.goooler.shadow")

  repositories {
    gradlePluginPortal()
    mavenCentral()
    mavenLocal()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.codemc.org/repository/nms/")
  }
    
  indra {
    javaVersions {
      target(8)
      minimumToolchain(8)
    }
  }
	
  dependencies {
    api("org.jetbrains:annotations:24.0.1")
	}
	
  spotless {
    java {
      licenseHeaderFile("$rootDir/header/header.txt")
      trimTrailingWhitespace()
      indentWithSpaces(2)
    }
    kotlinGradle {
      trimTrailingWhitespace()
      indentWithSpaces(2)
    }
  }

  tasks {
    compileJava {
      dependsOn("spotlessApply")
      options.compilerArgs.add("-parameters")
    }
    
    shadowJar {
      archiveBaseName.set(project.name)
      minimize()
      
      // Package expected to use as final directory for dependencies used.
      val relocationFinalPackage = "site.dragonstudio.visual.libs"
      
      relocate("org.jetbrains.annotations", "$relocationFinalPackage.org.jetbrains.annotations")
    }
  }
  
  publishing {
    publications {
      create<MavenPublication>("maven") {
        from(components["java"])
      }
    }
  }
}
