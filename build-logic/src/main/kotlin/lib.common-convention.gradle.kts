plugins {
    java
}

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.codemc.org/nms/")
}

tasks {
    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.compilerArgs.add("-parameters")
    }
}