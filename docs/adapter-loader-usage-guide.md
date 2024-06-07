# How to use the library
The library provides a single interface that works as adapter variating on the server version, 
this interface include methods for titles, action-bars and boss-bars creation, and for the tab-list
header and footer content modification.

Also, it count with the Server Version Adapter Loader, which must be used to get the correct
adapter for the on-running server version, or load a custom version adapter.

We have a basic test plugin as example using this, check it out [here](https://github.com/DragonStudio-Store/ds-visual/blob/main/test-plugin/src/main/java/site/dragonstudio/visual/TestPlugin.java).
However, let's go to check description and functionality of methods available on the [ServerVersionAdapterLoader](https://github.com/DragonStudio-Store/ds-visual/blob/main/api/src/main/java/site/dragonstudio/visual/adapter/ServerVersionAdapterLoader.java).

### ServerVersionAdapterLoader#decide
This method execution will check the current on-running version that is using the server, and will use it
to determinate the necessary version-adapter for compatibility with that version.

First that all, the method invokes the `ServerVersionChecker#verify`, which uses an enum that stores the
pre-defined supported minecraft versions for this library, for determinate if there's an available adapter for the 
current server version, if it is, the method will search the class that inherites from the version adapter model,
and will initialize it and return their instance to be used. In case that the correspond-to-version adapter won't be
included, the method return value will be `null`. Check the [loader](https://github.com/DragonStudio-Store/ds-visual/blob/main/api/src/main/java/site/dragonstudio/visual/adapter/ServerVersionAdapterLoader.java#L40-L70)
for more understanding behind method function.

```java
/**
   * Returns a version adapter model based on the current minecraft
   * version running on the server.
   *
   * @return The {@link VersionAdapterModel} impl, or {@code null} if
   * the minecraft version is not supported, or due to an internal error.
   * @see ServerVersionChecker#verify()
   * @see ServerVersionChecker#MINECRAFT_VERSION_PACKAGE
   * @since 1.0.0
   */
  public static @Nullable VersionAdapterModel decide() {
    // If checker has not detected compatible versions running on the
    // server, will return null.
    if (!ServerVersionChecker.verify()) {
      return null;
    }
    try {
      // We search by the adapter class, and we replace the '%s' placeholder, with
      // the current server minecraft release.
      final Class<?> serverVersionAdapterClass = Class.forName(String.format(
          VERSION_ADAPTERS_PACKAGE, MINECRAFT_VERSION_PACKAGE));
      // We get the constructor for the adapter class, and create a new instance
      // to be returned.
      return (VersionAdapterModel) serverVersionAdapterClass.getConstructor().newInstance();
    } catch (final Exception exception) {
      exception.printStackTrace();
      // An exception never should not happen during this process, unless you has
      // forgotten to include the adapter implementation for a single or many versions.
      return null;
    }
  }
```

### ServerVersionAdapterLoader#custom (Temporal, soon for removing)
This method execution will search by custom version-adapters for the non-supported minecraft versions by default,
using the given package for adapters search.

The method single function is to search by the custom-adapter correspond to the server version, on the defined
package where is stored. If an exception is triggered during method invocation, it will return null. Otherwise,
once the class for the custom-adapter have been instantiated, will return their instance.

This method is a temporal solution for support Minecraft 1.16.5+ versions, due to [already commented mishap here](https://github.com/DragonStudio-Store/ds-visual/blob/main/docs/README.md#ds-visual).
Check the [loader](https://github.com/DragonStudio-Store/ds-visual/blob/main/api/src/main/java/site/dragonstudio/visual/adapter/ServerVersionAdapterLoader.java#L72-L99)
for more understanding behind method function.
```java
/**
   * Returns a custom version adapter impl for the current on-running
   * minecraft version, using the given directory for initialization.
   *
   * @param directory the custom adapter package.
   * @return The {@link VersionAdapterModel} impl, or {@code null} if
   * an exception was triggered.
   * @see ServerVersionChecker#MINECRAFT_VERSION_PACKAGE
   * @since 1.0.0
   */
  @ApiStatus.Experimental
  public static @Nullable VersionAdapterModel custom(final @NotNull String directory) {
    try {
      // We search by the custom adapter class, the given directory must have
      // the '%s' which will be replaced with the current server version.
      //
      // e.g. io.github.aivruu.plugin.adapter.v1_20_R3.CustomAdapterImpl
      //
      // [MINECRAFT_VERSION_PACKAGE] would correspond to the current version release, v1_20_R3.
      final Class<?> serverVersionAdapterClass = Class.forName(String.format(directory, MINECRAFT_VERSION_PACKAGE));
      // We get the constructor for the adapter class, and create a new instance
      // to be returned.
      return (VersionAdapterModel) serverVersionAdapterClass.getConstructor().newInstance();
    } catch (final Exception exception) {
      exception.printStackTrace();
      return null;
    }
  }
```
Check how to make usage of version-adapter 
[here](https://github.com/DragonStudio-Store/ds-visual/blob/main/docs/adapter-usage-guide.md)
