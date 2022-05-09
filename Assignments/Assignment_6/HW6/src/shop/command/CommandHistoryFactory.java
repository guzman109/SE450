package shop.command;


/**
 * The CommandHistoryFactory class is a factory class that creates a new instance of the
 * CommandHistoryObj class
 */
public class CommandHistoryFactory {
  private CommandHistoryFactory() {}
  /**
   * Create a new CommandHistory object and return it.
   * 
   * @return A new instance of the CommandHistoryObj class.
   */
  static public CommandHistory newCommandHistory() {
    // TODO
    return new CommandHistoryObj();
  }
}
