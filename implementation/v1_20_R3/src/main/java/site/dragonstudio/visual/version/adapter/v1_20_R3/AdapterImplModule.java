//
// Copyright (C) 2024 DragonStudios - repo-viewer
//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program. If not, see <https://www.gnu.org/licenses/>.
//
package site.dragonstudio.visual.version.adapter.v1_20_R3;

import net.kyori.adventure.text.Component;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetSubtitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitlesAnimationPacket;
import net.minecraft.network.protocol.game.ClientboundTabListPacket;
import net.minecraft.server.network.ServerPlayerConnection;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import site.dragonstudio.visual.version.VersionAdapterModel;

/**
 * {@link VersionAdapterModel} implementation for Minecraft 1.20.3
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
