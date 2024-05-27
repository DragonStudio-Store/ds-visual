plugins {
    id("lib.publishing-convention")
}

dependencies {
    implementation(project(":ds-visual-api"))
    compileOnly("org.spigotmc:spigot:1.16.5-R0.1.SNAPSHOT")
}