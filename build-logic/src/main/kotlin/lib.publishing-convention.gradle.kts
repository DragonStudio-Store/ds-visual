plugins {
    id("lib.common-convention")
    `maven-publish`
	`java-library`
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}