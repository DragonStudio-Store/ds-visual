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

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import site.dragonstudio.visual.adapter.ServerVersionAdapterLoader;
import site.dragonstudio.visual.version.VersionAdapterModel;

public final class TestPlugin extends JavaPlugin {
  private VersionAdapterModel<String> legacyVersionAdapter;

  @Override
  public void onLoad() {
    // Minecraft 1.8.8 adapter uses strings for the packets content.
    this.legacyVersionAdapter = (VersionAdapterModel<String>) ServerVersionAdapterLoader.of(SupportVersionEnum.V1_8_R3);
  }

  @Override
  public void onEnable() {
    if (this.legacyVersionAdapter == null) {
      throw new IllegalStateException();
    }
    super.getCommand("visual").setExecutor((sender, command, label, args) -> {
      if (!(sender instanceof Player)) {
        return false;
      }
      final Player player = (Player) sender;
      if (args.length == 0) {
        return false;
      }
      switch (args[0]) {
        case "title":
          this.legacyVersionAdapter.sendPacketForTitle(player, "Title", "Subtitle", 20, 60, 20);
          break;
        case "actionbar":
          this.legacyVersionAdapter.sendPacketForActionBar(player, "DS-Visual ActionBar Example");
          break;
        case "tablist":
          this.legacyVersionAdapter.sendPacketForHeaderAndFooter(player, "Header", "Footer");
          break;
        default:
          player.sendMessage("Invalid Given Argument");
      }
      return false;
    });
  }
}
