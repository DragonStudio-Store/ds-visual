package site.dragonstudio.visual.adapter;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.Nullable;
import site.dragonstudio.visual.ServerVersionChecker;
import site.dragonstudio.visual.version.VersionAdapterModel;

/**
 * This class is used to perform automatic version-adapters
 * loading based on the server minecraft version.
 *
 * @since 1.0.0
 */
public final class ServerVersionAdapterLoader {
  /**
   * Correspond to the package and class name that use the adapters
   * for the supported Minecraft versions.
   */
  private static final String VERSION_ADAPTERS_PACKAGE = "site.dragonstudio.visual.version.adapter.%s.AdapterImplModule";

  /**
   * Returns a version adapter model based on the current minecraft
   * version running on the server.
   *
   * @return The {@link VersionAdapterModel} impl, or {@code null} if
   * the minecraft version is not supported, or due to an internal error.
   * @since 1.0.0
   */
  public static @Nullable VersionAdapterModel decide() {
    // If checker has not detected compatible versions running on the
    // server, will return null.
    if (!ServerVersionChecker.verify()) {
      return null;
    }
    final String serverVersionPackage = Bukkit.getServer().getClass().getPackage().getName();
    final String serverMinecraftRelease = serverVersionPackage.substring(serverVersionPackage.lastIndexOf('.') + 1);
    try {
      // We search by the adapter class, and we replace the '%s' placeholder, with
      // the current server minecraft release.
      final Class<?> serverVersionAdapterClass = Class.forName(String.format(
          VERSION_ADAPTERS_PACKAGE, serverMinecraftRelease));
      // We get the constructor for the adapter class, and create a new instance
      // to be returned.
      return (VersionAdapterModel) serverVersionAdapterClass.getConstructor().newInstance();
    } catch (final Exception exception) {
      exception.printStackTrace();
      // An exception should not happen during this process, unless you has forgotten
      // to include the version-adapter impl for a single or many versions.
      return null;
    }
  }
}
