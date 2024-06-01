/*
 * Copyright (C) 2024 DragonStudios - DS-Visual
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package site.dragonstudio.visual;

import java.util.Locale;
import java.util.logging.Logger;
import org.bukkit.Bukkit;

/**
 * This class is used to perform a version-check during adapter
 * loading process to indicate if server-version is supported or not.
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
