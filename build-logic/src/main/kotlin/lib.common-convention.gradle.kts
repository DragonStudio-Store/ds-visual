plugins {
    java
}

java {
  toolchain.languageVersion.set(JavaLanguageVersion.of(8))
}

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.codemc.org/nms/")
}


dependencies {
  implementation("org.jetbrains:annotations:24.0.1")
}

tasks {
    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.compilerArgs.add("-parameters")
    }
}