package studio.pixellite.tablist.dispatcher;

import me.lucko.helper.Schedulers;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import studio.pixellite.tablist.TablistPlugin;
import studio.pixellite.tablist.util.ComponentDeserializer;

/**
 * A dispatcher for sending playerlist header and footers to all online players.
 */
public class TablistDispatcher {
  private final TablistPlugin plugin;

  public TablistDispatcher(TablistPlugin plugin) {
    this.plugin = plugin;
  }

  /**
   * Dispatches the configured tablist to all online players.
   */
  public void dispatchToOnlinePlayers() {
    if(Bukkit.getOnlinePlayers().size() == 0) {
      return; // nobody is online, nothing to do here
    }

    // Get header and footer
    String header = plugin.getParser().parsePlaceholders(
            String.join("\n", plugin.getConfigurationStringList("header")));

    String footer = plugin.getParser().parsePlaceholders(
            String.join("\n", plugin.getConfigurationStringList("footer")));

    // Convert to components
    Component headerComp = ComponentDeserializer.deserialize(header);
    Component footerComp = ComponentDeserializer.deserialize(footer);

    // Send to all online players
    for(Player player : Bukkit.getOnlinePlayers()) {
      Schedulers.async().run(() -> {
        player.sendPlayerListHeader(headerComp);
        player.sendPlayerListFooter(footerComp);
      });
    }
  }
}
