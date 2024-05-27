plugins {
    id("lib.common-convention")
}

dependencies {
    implementation(project(":ds-visual-1_8_R3"))
	
    compileOnly(libs.platform)
}