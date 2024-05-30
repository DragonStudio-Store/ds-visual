package site.dragonstudio.visual;

import java.util.Locale;
import java.util.logging.Logger;
import org.bukkit.Bukkit;

/**
 * Once this class is on class-loading process, check if the version
 * running on the current server is supported by the library.
 *
 * @since 1.0.0
 */
public final class ServerVersionChecker {
  /**
   * The {@link Logger} instance used to notify errors during class-loading
   * process due to invalid server version detected.
   */
  private static final Logger LOGGER = Logger.getLogger("ServerVersionChecker");

  /**
   * Verifies if the current on-running server version is supported
   * by the library.
   *
   * @return Whether the running version is supported.
   * @since 1.0.0
   */
  public static boolean verify() {
    // e.g. org.bukkit.craftbukkit.v1_8_R3
    final String packageName = Bukkit.getServer().getClass().getPackage().getName();
    // e.g. org.bukkit.craftbukkit.v1_8_R3 -> 'v1_8_R3'
    final String minecraftRelease = packageName.substring(packageName.lastIndexOf('.') + 1);
    try {
      // We try to get a enum-value from the [SupportVersionEnum] class to
      // validate the version, if the operation thrown an exception, indicate
      // that the current server-version isn't supported.
      SupportVersionEnum.valueOf(minecraftRelease.toUpperCase(Locale.ROOT));
      return true;
    } catch (final IllegalArgumentException exception) {
      LOGGER.severe("Your server has a non-supported minecraft version by the DS-Visual library!");
      LOGGER.severe("Supported Versions - 1.8.8, 1.9.4, 1.10.2, 1.11.2, 1.12.2, 1.13.2, 1.14.4, 1.15.2, 1.16.5");
      return false;
    }
  }
}