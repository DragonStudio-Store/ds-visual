# How to use the Version Adapter Model
This is the interface that the library uses as bridge for all the pre-defined
version adapters, and also, for the custom version adapters.

It counts with methods to send titles, action-bars and boss-bars, or even make modifications
to the player tab-list, including changes for the header and footer.

A very basic example using the adapter methods, check it out [here](https://github.com/DragonStudio-Store/ds-visual/blob/main/test-plugin/src/main/java/site/dragonstudio/visual/TestPlugin.java).
Let's go to check description and functionality of methods available on the [ServerVersionAdapterLoader](https://github.com/DragonStudio-Store/ds-visual/blob/main/api/src/main/java/site/dragonstudio/visual/adapter/ServerVersionAdapterLoader.java).

### VersionAdapterModel#sendPacketForTitle
This method execution will send packets with the titles information to the
player's client.

```java
/**
 * Sends a packet to the player for the titles send.
 *
 * @param player the player to who send the packet.
 * @param title the text for the title, could be null
 *              for no title send.
 * @param subtitle the text for the subtitle, could be
 *                 null for no subtitle send.
 * @param fadeIn Amount of time for fully title appear.
 * @param stay Total time for title stay on screen.
 * @param fadeOut Amount of time for fully title disappear.
 * @since 1.0.0
 */
void sendPacketForTitle(final @NotNull Player player, final @Nullable String title, final @Nullable String subtitle,
                        final int fadeIn, final int stay, final int fadeOut);
```

### VersionAdapterModel#sendPacketForActionBar
This method execution will send a packet with the action-bar information to the
player's client.

```java
/**
 * Sends a packet to the player for the single subtitle send.
 *
 * @param player the player to who send the packet.
 * @param text the text for the subtitle.
 * @since 1.0.0
 */
void sendPacketForActionBar(final @NotNull Player player, final @NotNull String text);
```

### VersionAdapterModel#sendPacketForHeaderAndFooter
This method execution will send a packet with the header and footer information
for the tab-list, to the player's client.

```java
/**
 * Sends a packet to the player for the header and footer
 * modification.
 *
 * @param player the player to who send the packet.
 * @param header the text for the header, could be null
 *               for no header modification.
 * @param footer the text for the footer, could be null
 *               for no footer modification.
 * @since 1.0.0
 */
void sendPacketForHeaderAndFooter(final @NotNull Player player, final @Nullable String header,
                                  final @Nullable String footer);
```

### VersionAdapterModel#sendPacketForBossBar
...

```java
... ?
```
