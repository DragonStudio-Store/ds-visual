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
   * @see ServerVersionChecker#verify()
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
      // An exception never should not happen during this process, unless you has
      // forgotten to include the adapter implementation for a single or many versions.
      return null;
    }
  }
}
