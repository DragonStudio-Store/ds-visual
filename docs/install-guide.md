# How I can import the library
The library can be installed and imported into your project classpath from two ways,
using a dependency-manager, such as Gradle or Maven. Or directly using the jar files provided
with each published release on GitHub.

-> [Latest Published Release](https://github.con/DragonStudio-Store/ds-visual/releases/latest)

```kotlin
repositories {
  maven("https://jitpack.io/")
}

dependencies {
  // Replace 'VERSION' with tag-version of the latest release.
  implementation("com.github.DragonStudio-Store:ds-visual:VERSION") // Include all library implementations/subprojects.
  implementation("com.github.DragonStudio-Store:ds-visual-api:VERSION") // Only includes the version adapter model, and the adapter loader.
  // Includes the version adapter for that minecraft release, you can see the default available adapters here.
  // -> https://github.com/DragonStudio-Store/ds-visual/tree/main/version
  implementation("com.github.DragonStudio-Store:ds-visual-1_8_R3:VERSION")
}

shadowJar {
  // Relocate DS-Visual library used code into own project package for
  // avoid possible errors due to same directory on several plugins.
  relocate("site.dragonstudio.visual", "com.yourpackage.libs.visual")
}
```

```xml
<repositories>
    <repository>
        <id>jitpack</id>
        <url>https://jitpack.io/</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.DragonStudio-Store</groupId>
        <artifactId>ds-visual</artifactId>
        <version>VERSION</version>
        <scope>compile</scope>
    </dependency>
</dependencies>

<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-shade-plugin</artifactId>
            <version>3.6.0</version>
            <executions>
                <execution>
                    <phase>package</phase>
                    <goals>
                        <goal>shade</goal>
                    </goals>
                </execution>
            </executions>
            <configuration>
                <relocations>
                    <relocation>
                        <pattern>site.dragonstudio.visual</pattern>
                        <!-- Define the package name that you'll use for library code relocation -->
                        <shadedPattern>com.yourpackage.libs.visual</shadedPattern>
                    </relocation>
                </relocations>
            </configuration>
        </plugin>
    </plugins>
</build>
```
