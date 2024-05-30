package site.dragonstudio.visual.version;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * This interface represents an inheritable model that is
 * used to implement code adaptations for the supported
 * Minecraft versions.
 *
 * @since 1.0.0
 */
public interface VersionAdapterModel {
  /** The JSON text used for input content given for send packets. */
  String PACKET_JSON_TEXT = "{\"text\": \"%s\"}";

  /**
   * Sends a packet to the player for the titles send.
   *
   * @param player the player to who send the packet.
   * @param title the text for the title, can be empty
   *              for no title send.
   * @param subtitle the text for the subtitle, can be
   *                 empty for no subtitle send.
   * @param fadeIn Amount of time for fully title appear.
   * @param stay Total time for title stay on screen.
   * @param fadeOut Amount of time for fully title disappear.
   * @since 1.0.0
   */
  void sendPacketForTitle(final @NotNull Player player, final @Nullable String title, final @Nullable String subtitle,
                          final int fadeIn, final int stay, final int fadeOut);

  /**
   * Sends a packet to the player for the single subtitle send.
   *
   * @param player the player to who send the packet.
   * @param text the text for the subtitle.
   * @since 1.0.0
   */
  void sendPacketForActionBar(final @NotNull Player player, final @NotNull String text);

  /**
   * Sends a packet to the player for the header and footer
   * modification.
   *
   * @param player the player to who send the packet.
   * @param header the text for the header, can be empty
   *               for no header modification.
   * @param footer the text for the footer, can be empty
   *               for no footer modification.
   * @since 1.0.0
   */
  void sendPacketForHeaderAndFooter(final @NotNull Player player, final @Nullable String header,
                                    final @Nullable String footer);
}
