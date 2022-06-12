package studio.pixellite.tablist.parser;

/**
 * An abstract utility for parsing placeholders in a string. Mostly used for sending player tablist
 * headers and footers.
 */
public interface PlaceholderParser {
  /**
   * Parses all placeholders in a string.
   *
   * @param string the string to parse
   * @return the parsed string
   */
  String parsePlaceholders(String string);
}
