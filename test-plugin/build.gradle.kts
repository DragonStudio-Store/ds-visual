plugins {
    id("lib.common-convention")
}

dependencies {
    implementation(project(":ds-visual-api"))
	
    compileOnly(libs.platform)
}