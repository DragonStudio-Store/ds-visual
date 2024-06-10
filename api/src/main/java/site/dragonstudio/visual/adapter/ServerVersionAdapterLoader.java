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

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import site.dragonstudio.visual.ServerVersionChecker;
import site.dragonstudio.visual.SupportVersionEnum;
import site.dragonstudio.visual.version.VersionAdapterModel;

import static site.dragonstudio.visual.ServerVersionChecker.MINECRAFT_VERSION_PACKAGE;

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
}
