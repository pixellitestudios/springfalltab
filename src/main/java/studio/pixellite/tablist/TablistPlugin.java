package studio.pixellite.tablist;

import com.google.common.reflect.TypeToken;
import me.lucko.helper.Schedulers;
import me.lucko.helper.config.ConfigurationNode;
import me.lucko.helper.config.objectmapping.ObjectMappingException;
import me.lucko.helper.plugin.ExtendedJavaPlugin;
import org.bukkit.Bukkit;
import studio.pixellite.tablist.dispatcher.TablistDispatcher;
import studio.pixellite.tablist.parser.PlaceholderParser;
import studio.pixellite.tablist.parser.impl.NetworkPlaceholderParser;
import studio.pixellite.tablist.parser.impl.SimplePlacehlderParser;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TablistPlugin extends ExtendedJavaPlugin {
  private ConfigurationNode configuration;
  private PlaceholderParser parser;
  private TablistDispatcher dispatcher;

  @Override
  protected void enable() {
    // Load configuration
    saveDefaultConfig();
    configuration = loadConfigNode("config.yml");

    // Load parser
    if(Bukkit.getPluginManager().isPluginEnabled("PixelliteNetwork")) {
      parser = new NetworkPlaceholderParser();
    } else {
      parser = new SimplePlacehlderParser();
    }

    // Load tablist dispatcher
    dispatcher = new TablistDispatcher(this);

    // Setup recurring call to dispatch tablist
    Schedulers.sync().runRepeating(dispatcher::dispatchToOnlinePlayers,
            1,
            TimeUnit.SECONDS,
            3,
            TimeUnit.SECONDS);
  }

  public ConfigurationNode getConfiguration() {
    return configuration;
  }

  public PlaceholderParser getParser() {
    return parser;
  }

  public TablistDispatcher getDispatcher() {
    return dispatcher;
  }

  /**
   * Gets and error-handles a string list from the configuration.
   *
   * @param path the path to the requested list
   * @return the list, if successful
   */
  public List<String> getConfigurationStringList(Object... path) {
    try {
      return configuration.getNode(path).getList(TypeToken.of(String.class));
    } catch (ObjectMappingException e) {
      throw new RuntimeException(e);
    }
  }
}
