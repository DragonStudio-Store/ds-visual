plugins {
    id("lib.publishing-convention")
}

dependencies {
    implementation(project(":ds-visual-api"))
    compileOnly("org.spigotmc:spigot:1.13.2-R0.1-SNAPSHOT")
}
