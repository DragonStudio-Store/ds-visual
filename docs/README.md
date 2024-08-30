# ds-visual | [![Codacy Badge](https://app.codacy.com/project/badge/Grade/2af8dd31da77439ea518ee1df8d725be)](https://app.codacy.com/gh/DragonStudio-Store/ds-visual/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_grade)
[![](https://jitpack.io/v/DragonStudio-Store/ds-visual.svg)](https://jitpack.io/#DragonStudio-Store/ds-visual)
![GitHub Actions Workflow Status](https://img.shields.io/github/actions/workflow/status/DragonStudio-Store/ds-visual/build.yml)
![GitHub License](https://img.shields.io/github/license/DragonStudio-Store/ds-visual)
![GitHub commit activity](https://img.shields.io/github/commit-activity/t/DragonStudio-Store/ds-visual)

`ds-visual` permits the creation of Titles, Action-Bars, Boss-Bars and even make modifications to the player's tab-list's header / footer, with the possiblity
of make them updatable for animations creation, all this made at packet-level using NMS (net.minecraft.server), without the use of any external or dependable
packet-wrapper for these functionalities

## Features
- Send Titles and ActionBars.
- Modify players tab-list content.
- Custom Boss-Bars creation with updatable possibility.
- Easiest for use.
- [MiniMessage](https://docs.advntr.dev/minimessage/format.html) support for `1.17.1 - 1.20.6` versions.

## Future Features
- Custom BossBar creation.

## Javadoc
The code is very well documented to avoid any confusion that developers could have when they uses the library, of same way, the project provides javadocs for
each project's module's source-code, you can check the project-documentation at [here](https://javadoc.jitpack.io/com/github/DragonStudio-Store/ds-visual/latest/javadoc/index.html),
and selecting the module or implementation to check.

## Guides
* [Library's Installation.](https://github.com/DragonStudio-Store/ds-visual/blob/main/docs/install-guide.md)
* [Required-JDK for specific-versions.](https://github.com/DragonStudio-Store/ds-visual/blob/main/docs/java-version-guide.md);
* [How to load an implementation for library use.](https://github.com/DragonStudio-Store/ds-visual/blob/main/docs/adapter-loader-usage-guide.md)
* [How to use library's features.](https://github.com/DragonStudio-Store/ds-visual/blob/main/docs/adapter-usage-guide.md)

## Contribution
Do you want to contribute the project? Great! Simplely fork the project repository, and make the changes that you require to do, then create a pull-request to
this repository. We're agree to accept any changes that are helpful for developement, such like features, explots or bugs fixing.

## Code of Conduct
We expect all contributors to adhere to our [Code of Conduct](../CODE_OF_CONDUCT.md). Please read it to understand the expected behavior in our community.

## Building
This library use Gradle-Kotlin for project management and building. If you want to contribute and build the project, you must to follow the next requirements:

1. Gradle-Kotlin installed at your pc.
2. Java 8+.

Here an small example for project-cloning using git, and building with gradle:
```
git clone https://github.com/DragonStudio-Store/ds-visual.git
cd ds-visual
./gradlew build
```

## Support
If you have any questions, suggestions, or problems to use the library, join to our [Discord](https://discord.dragonstudio.site/) server and open a support-ticket.
