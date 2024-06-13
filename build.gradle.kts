plugins {
    `java-library`
    `maven-publish`
    alias(libs.plugins.spotless)
}

subprojects {
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")
    apply(plugin = "com.diffplug.spotless")

    repositories {
        gradlePluginPortal()
        mavenCentral()
        mavenLocal()
		    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
        maven("https://repo.papermc.io/repository/maven-public/")
        maven("https://repo.codemc.org/repository/nms/")
    }
    
    java {
	      toolchain.languageVersion.set(JavaLanguageVersion.of(8))
	  }
	
	  dependencies {
	      api("org.jetbrains:annotations:24.0.1")
	  }
	
    spotless {
        java {
            licenseHeaderFile("$rootDir/license/header.txt")
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
		    javadoc {
            options.encoding = Charsets.UTF_8.name()
			      destinationDir = file("javadoc")
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
