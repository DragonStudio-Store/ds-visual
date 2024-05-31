package site.dragonstudio.visual.version.adapter.v1_15_R1;

import io.netty.buffer.Unpooled;
import java.io.IOException;
import net.minecraft.server.v1_15_R1.ChatMessageType;
import net.minecraft.server.v1_15_R1.IChatBaseComponent;
import net.minecraft.server.v1_15_R1.PacketDataSerializer;
import net.minecraft.server.v1_15_R1.PacketPlayOutChat;
import net.minecraft.server.v1_15_R1.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_15_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_15_R1.PlayerConnection;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import site.dragonstudio.visual.version.VersionAdapterModel;

/**
 * {@link VersionAdapterModel} implementation for Minecraft 1.15.2
 * support.
 *
 * @since 1.0.0
 */
public final class AdapterImplModule implements VersionAdapterModel {
  private PlayerConnection connection;
  private PacketPlayOutTitle clientsideTitlePacket;
  private PacketPlayOutChat clientsideActionBarPacket;
  private PacketPlayOutPlayerListHeaderFooter clientsideHeaderAndFooterPacket;
  private PacketDataSerializer packetDataWriter;

  @Override
  public void sendPacketForTitle(final @NotNull Player player, final @Nullable String title,
                                 final @Nullable String subtitle, final int fadeIn, final int stay, final int fadeOut) {
    this.connection = ((CraftPlayer) player).getHandle().playerConnection;
    this.clientsideTitlePacket = new PacketPlayOutTitle(fadeIn, stay, fadeOut);
    // Check if the title was defined to be sent.
    if (title != null) {
      this.clientsideTitlePacket = new PacketPlayOutTitle(
          PacketPlayOutTitle.EnumTitleAction.TITLE,
          IChatBaseComponent.ChatSerializer.a(String.format(PACKET_JSON_TEXT, title)));
      this.connection.sendPacket(this.clientsideTitlePacket);
    }
    // Check if the subtitle was defined to be sent.
    if (subtitle != null) {
      this.clientsideTitlePacket = new PacketPlayOutTitle(
          PacketPlayOutTitle.EnumTitleAction.SUBTITLE,
          IChatBaseComponent.ChatSerializer.a(String.format(PACKET_JSON_TEXT, subtitle)));
      this.connection.sendPacket(this.clientsideTitlePacket);
    }
  }

  @Override
  public void sendPacketForActionBar(final @NotNull Player player, final @NotNull String text) {
    this.connection = ((CraftPlayer) player).getHandle().playerConnection;
    this.clientsideActionBarPacket = new PacketPlayOutChat(
        IChatBaseComponent.ChatSerializer.a(String.format(PACKET_JSON_TEXT, text)),
        ChatMessageType.GAME_INFO);
    this.connection.sendPacket(this.clientsideActionBarPacket);
  }

  @Override
  public void sendPacketForHeaderAndFooter(final @NotNull Player player, final @Nullable String header,
                                           final @Nullable String footer) {
    this.connection = ((CraftPlayer) player).getHandle().playerConnection;
    this.clientsideHeaderAndFooterPacket = new PacketPlayOutPlayerListHeaderFooter();
    // We write the data for the tab-list modifier packet before
    // send the packet, if any text is null which indicates non-modification,
    // we give an empty stream, instead we pass the header merged with [PACKET_JSON_TEXT]
    // content.
    this.packetDataWriter = new PacketDataSerializer(Unpooled.buffer());
    try {
      this.packetDataWriter.a((header != null)
          ? IChatBaseComponent.ChatSerializer.a(String.format(PACKET_JSON_TEXT, header))
          : IChatBaseComponent.ChatSerializer.a(""));
      this.packetDataWriter.a((footer != null)
          ? IChatBaseComponent.ChatSerializer.a(String.format(PACKET_JSON_TEXT, footer))
          : IChatBaseComponent.ChatSerializer.a(""));
      // We give to the packet the [PacketDataSerializer] instance that
      // we used to define packet data.
      this.clientsideHeaderAndFooterPacket.a(this.packetDataWriter);
    } catch (final IOException exception) {
      exception.printStackTrace();
    }
    // Send packet to the player client.
    this.connection.sendPacket(this.clientsideHeaderAndFooterPacket);
  }
}
