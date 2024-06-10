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
package site.dragonstudio.visual.version;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * This interface represents an inheritable model that is
 * used to implement code adaptations for the supported
 * Minecraft versions.
 *
 * @param <T> The object type that will use the adapter,
 *            can be a simple string, or a modern component.
 * @since 1.0.0
 */
public interface VersionAdapterModel<T> {
  /** The JSON text used for input content given for send packets. */
  String PACKET_JSON_TEXT = "{\"text\": \"%s\"}";

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
  void sendPacketForTitle(final @NotNull Player player, final @Nullable T title, final @Nullable T subtitle,
                          final int fadeIn, final int stay, final int fadeOut);

  /**
   * Sends a packet to the player for the single subtitle send.
   *
   * @param player the player to who send the packet.
   * @param text the text for the subtitle.
   * @since 1.0.0
   */
  void sendPacketForActionBar(final @NotNull Player player, final @NotNull T text);

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
  void sendPacketForHeaderAndFooter(final @NotNull Player player, final @Nullable T header,
                                    final @Nullable T footer);
}
