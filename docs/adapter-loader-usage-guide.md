# How to use the library
The library provides a single interface that works as adapter variating on the server version, 
this interface include methods for titles, action-bars and boss-bars creation, and for the tab-list
header and footer content modification.

Also, it count with the Server Version Adapter Loader, which must be used to get the correct
adapter for the on-running server version, or load a custom version adapter.

We have a basic test plugin as example using this, check it out [here](https://github.com/DragonStudio-Store/ds-visual/blob/main/test-plugin/src/main/java/site/dragonstudio/visual/TestPlugin.java).
However, let's go to check description and functionality of methods available on the [ServerVersionAdapterLoader](https://github.com/DragonStudio-Store/ds-visual/blob/main/api/src/main/java/site/dragonstudio/visual/adapter/ServerVersionAdapterLoader.java).

## ServerVersionAdapterLoader#decide
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
  public static @Nullable VersionAdapterModel<?> decide() {
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
      return (VersionAdapterModel<?>) serverVersionAdapterClass.getConstructor().newInstance();
    } catch (final Exception exception) {
      exception.printStackTrace();
      // An exception never should not happen during this process, unless you has
      // forgotten to include the adapter implementation for a single or many versions.
      return null;
    }
  }
```

## ServerVersionAdapterLoader#of
This method execution will search and initialize the version-adapter for the version specified as parameter.

The method simple function is search by the adapter class for the specified Minecraft version, it could return
null due to an exception. This shouldn't happen, because exact adapter version is always defined, and the
method will use the [adapters-package](https://github.com/DragonStudio-Store/ds-visual/blob/main/api/src/main/java/site/dragonstudio/visual/adapter/ServerVersionAdapterLoader.java#L38) value and the minecraft version taken from the given enum value to perform adapter class searching.

Check the [loader](https://github.com/DragonStudio-Store/ds-visual/blob/main/api/src/main/java/site/dragonstudio/visual/adapter/ServerVersionAdapterLoader.java#L72-L99)
for more understanding behind method function.
```java
  /**
   * Returns the version adapter implementation for the specified minecraft
   * version release.
   * 
   * @param supportedVersionEnumValue the minecraft release enum (e.g. {@link SupportVersionEnum#V1_20_R3}).
   * @return The version adapter type for the specified minecraft version. This
   * means that for versions lower to 1.16.5 you'll receive an adapter for with-string
   * usage, meanwhile for 1.16.5 the adapter will use modern components.
   * @since 1.0.0
   */
  public static @Nullable VersionAdapterModel<?> of(final @NotNull SupportVersionEnum supportedVersionEnumValue) {
    try {
      final String supportedVersionValue = supportedVersionEnumValue.name();
      // We search by the adapter class, and we replace the '%s' placeholder, with
      // the to-string converted enum value replacing the upper case 'V' character,
      // into a lower case.
      final Class<?> serverVersionAdapterClass = Class.forName(String.format(
          VERSION_ADAPTERS_PACKAGE, supportedVersionValue.replace("V", "v")));
      // We get the constructor for the adapter class, and create a new instance
      // to be returned.
      return (VersionAdapterModel<?>) serverVersionAdapterClass.getConstructor().newInstance();
    } catch (final Exception exception) {
      exception.printStackTrace();
      // This method should never throw an exception, because the given version value
      // will always the same.
      return null;
    }
  }
```
Check how to make usage of version-adapter 
[here](https://github.com/DragonStudio-Store/ds-visual/blob/main/docs/adapter-usage-guide.md)
