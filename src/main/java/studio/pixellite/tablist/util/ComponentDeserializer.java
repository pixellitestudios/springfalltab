package studio.pixellite.tablist.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

/**
 * A simple utility class for deserializing strings to components using {@link MiniMessage}.
 */
public class ComponentDeserializer {
  private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();

  public static Component deserialize(String string) {
    return MINI_MESSAGE.deserialize(string);
  }

  // Ensure that this class cannot be instantiated
  private ComponentDeserializer() {}
}
