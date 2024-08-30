package site.dragonstudio.visual.version.adapter.v1_18_R2;

import net.kyori.adventure.text.Component;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetSubtitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitlesAnimationPacket;
import net.minecraft.network.protocol.game.ClientboundTabListPacket;
import net.minecraft.server.network.ServerPlayerConnection;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import site.dragonstudio.visual.version.VersionAdapterModel;

/**
 * {@link VersionAdapterModel} implementation for Minecraft 1.18.2
 * support.
 *
 * @since 1.0.0
 */
public final class AdapterImplModule implements VersionAdapterModel<Component> {
  private ServerPlayerConnection playerConnection;
  private ClientboundSetTitleTextPacket clientsideTitleTextPacket;
  private ClientboundSetSubtitleTextPacket clientsideSubtitleTextPacket;
  private ClientboundSetTitlesAnimationPacket clientsideTitleTimesPacket;
  private ClientboundSetActionBarTextPacket clientsideActionBarPacket;
  private ClientboundTabListPacket clientsideHeaderAndFooterPacket;

  @Override
  public void sendPacketForTitle(final @NotNull Player player, final @Nullable Component title,
                                 final @Nullable Component subtitle, final int fadeIn, final int stay,
                                 final int fadeOut) {
    this.playerConnection = ((CraftPlayer) player).getHandle().connection;
    this.clientsideTitleTimesPacket = new ClientboundSetTitlesAnimationPacket(fadeIn, stay, fadeOut);
    this.playerConnection.send(this.clientsideTitleTimesPacket);
    if (title != null) {
      this.clientsideTitleTextPacket = new ClientboundSetTitleTextPacket((net.minecraft.network.chat.Component) title);
      this.playerConnection.send(this.clientsideTitleTextPacket);
    }
    if (subtitle != null) {
      this.clientsideSubtitleTextPacket = new ClientboundSetSubtitleTextPacket(
          (net.minecraft.network.chat.Component) subtitle);
      this.playerConnection.send(this.clientsideSubtitleTextPacket);
    }
  }

  @Override
  public void sendPacketForActionBar(final @NotNull Player player, final @NotNull Component text) {
    this.playerConnection = ((CraftPlayer) player).getHandle().connection;
    this.clientsideActionBarPacket = new ClientboundSetActionBarTextPacket((net.minecraft.network.chat.Component) text);
    this.playerConnection.send(this.clientsideActionBarPacket);
  }

  @Override
  public void sendPacketForHeaderAndFooter(final @NotNull Player player, final @Nullable Component header,
                                           final @Nullable Component footer) {
    this.playerConnection = ((CraftPlayer) player).getHandle().connection;
    this.clientsideHeaderAndFooterPacket = new ClientboundTabListPacket(
        (header == null) ? (net.minecraft.network.chat.Component) Component.empty()
            : (net.minecraft.network.chat.Component) header,
        (footer == null) ? (net.minecraft.network.chat.Component) Component.empty()
            : (net.minecraft.network.chat.Component) footer);
    this.playerConnection.send(this.clientsideHeaderAndFooterPacket);
  }
}
