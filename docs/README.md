# ds-visual [![](https://jitpack.io/v/DragonStudio-Store/ds-visual.svg)](https://jitpack.io/#DragonStudio-Store/ds-visual)
`DSVisual` is a library designed for NMS-Packets usage for the creation of Titles, Action-bars, and realize modifications into player's tab-list, all for Bukkit platforms and with support for several Minecraft versions.

## Features
- Send Titles and ActionBars.
- Modify players tab-list content.
- Easiest for use.
- Direct [Minecraft Protocol](https://wiki.vg/Protocol) usage, without using external libraries with wrappers for packets handling.
- [MiniMessage](https://docs.advntr.dev/minimessage/format.html) support for `1.17.1 - 1.20.6` versions.

## Future Features
- Custom BossBar creation.

## Javadoc
Below there's the javadocs for each module of the library, to view the adapters you must define the subproject name, e.g. ds-visual-v1_8_R3.
Here you have an [example](https://javadoc.jitpack.io/com/github/DragonStudio-Store/ds-visual/ds-visual-v1_20_R3/latest/javadoc/) for adapter javadoc view.
- [API](https://javadoc.jitpack.io/com/github/DragonStudio-Store/ds-visual/ds-visual-api/latest/javadoc/) for Latest release.
- [Common](https://javadoc.jitpack.io/com/github/DragonStudio-Store/ds-visual/ds-visual-common/latest/javadoc/) for Latest release.

## Guides
* [Installation](https://github.com/DragonStudio-Store/ds-visual/blob/main/docs/install-guide.md); For library installation and classpath import.
* [Java Required Version](https://github.com/DragonStudio-Store/ds-visual/blob/main/docs/java-version-guide.md); Indicate the required Java version for every adapter.
* [Version Adapter Loader Usage](https://github.com/DragonStudio-Store/ds-visual/blob/main/docs/adapter-loader-usage-guide.md); How to use the version adapter loader.
* [Version Adapter Usage](https://github.com/DragonStudio-Store/ds-visual/blob/main/docs/adapter-usage-guide.md); How to make usage of the version-adapters.

## Contribution
Do you want to contribute the project? Great! Simplely fork the project repository, and make the changes that you require to do, then create a pull-request to
this repository. We're agree to accept any changes that are helpful for developement, like features addition, or bugs fixing will be accepted.

## Building
This library use Gradle with the Kotlin-DSL for project management
and building. Requirements to do this:

1. Gradle with Kotlin-DSL.
2. Java 8.
3. Git.
```
git clone https://github.com/DragonStudio-Store/ds-visual.git
cd ds-visual
./gradlew build
```

## Support
If you have any questions, suggestions, or problems to use the library, join to us [Discord](https://discord.dragonstudio.site/) server and open a support-ticket.
