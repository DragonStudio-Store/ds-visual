@file:Suppress("UnstableApiUsage")

rootProject.name = "ds-visual"

sequenceOf("api", "test-plugin", "common", 
           "v1_8_R3", "v1_9_R2", "v1_10_R1",
           "v1_11_R1", "v1_12_R1", "v1_13_R2",
           "v1_14_R1", "v1_15_R1", "v1_16_R3",
		   "v1_17_R1", "v1_18_R2", "v1_19_R3",
		   "v1_20_R1", "v1_20_R2", "v1_20_R3").forEach {
    val kerbalProject = ":${rootProject.name}-$it"
    include(kerbalProject)
	// Define subproject-dir for version-adapters
	if (it.startsWith("v")) {
        project(kerbalProject).projectDir = file("version/$it")
	} else {
	    project(kerbalProject).projectDir = file(it)
    }
}
