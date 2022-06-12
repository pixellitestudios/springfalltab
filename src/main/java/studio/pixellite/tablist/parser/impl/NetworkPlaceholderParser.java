package studio.pixellite.tablist.parser.impl;

import me.lucko.helper.Services;
import me.lucko.helper.network.Network;
import org.bukkit.Bukkit;
import studio.pixellite.tablist.parser.PlaceholderParser;

/**
 * A parser for parsing placeholders relating to the {@link Network} class.
 */
public class NetworkPlaceholderParser implements PlaceholderParser {
  private final Network network;

  public NetworkPlaceholderParser() {
    this.network = Services.get(Network.class).orElse(null);
  }

  @Override
  public String parsePlaceholders(String string) {
    if(network == null || network.isClosed()) {
      throw new UnsupportedOperationException("Network not found is is closed!");
    }

    // Replace placeholders
    return string.replace("{playercount}", Integer.toString(network.getOverallPlayerCount()))
            .replace("{onlinehere}", Integer.toString(Bukkit.getOnlinePlayers().size()))
            .replace("{maxhere}", Integer.toString(Bukkit.getMaxPlayers()));
  }
}
