plugins {
    id("lib.publishing-convention")
}

dependencies {
    implementation(project(":ds-visual-common"))

    compileOnly(libs.platform)
}
