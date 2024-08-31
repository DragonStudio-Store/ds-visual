@file:Suppress("UnstableApiUsage")

rootProject.name = "ds-visual"

sequenceOf("api", "test-plugin", "v1_8_R3", "v1_19_R3", "v1_20_R3", "v1_21_R1").forEach {
  val kerbalProject = ":${rootProject.name}-$it"
  include(kerbalProject)
	if (it.startsWith("v")) {
    project(kerbalProject).projectDir = file("implementation/$it")
	} else {
    project(kerbalProject).projectDir = file(it)
  }
}
