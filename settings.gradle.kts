pluginManagement {
	repositories {
        gradlePluginPortal()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
    includeBuild("build-logic")
}

rootProject.name = "ds-visual"

sequenceOf("api", "test-plugin", "common").forEach {
    include("${rootProject.name}-$it")
    project(":${rootProject.name}-$it").projectDir = file(it)
}

sequenceOf("1_8_R3", "1_9_R2", "1_10_R1",
           "1_11_R1", "1_12_R1", "1_13_R2",
           "1_14_R1", "1_15_R1", "1_16_R3")
  .forEach {
    include("${rootProject.name}-$it")
    project(":${rootProject.name}-$it").projectDir = file("version/v$it")
}
