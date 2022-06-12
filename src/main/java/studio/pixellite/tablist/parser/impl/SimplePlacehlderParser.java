package studio.pixellite.tablist.parser.impl;

import studio.pixellite.tablist.parser.PlaceholderParser;

public class SimplePlacehlderParser implements PlaceholderParser {
  @Override
  public String parsePlaceholders(String string) {
    return string; // no plugin present, no need to parse anything
  }
}
