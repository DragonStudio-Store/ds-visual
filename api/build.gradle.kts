plugins {
	id("lib.publishing-convention")
}

dependencies {
    implementation(project(":ds-visual-common"))

    compileOnly(libs.platform)
	testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.2")
}