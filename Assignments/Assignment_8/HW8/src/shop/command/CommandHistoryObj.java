package shop.command;
import java.util.Stack;

final class CommandHistoryObj implements CommandHistory {
  Stack<UndoableCommand> _undoStack = new Stack<UndoableCommand>();
  Stack<UndoableCommand> _redoStack = new Stack<UndoableCommand>();
  
  RerunnableCommand _undoCmd = new RerunnableCommand () {
      public boolean run () {
        boolean result = !_undoStack.empty();
        if (result) {
          // TODO  
          UndoableCommand cmd = _undoStack.pop();     
          cmd.undo();
          _redoStack.push(cmd);   
        }
        return result;
      }
    };
  RerunnableCommand _redoCmd = new RerunnableCommand () {
      public boolean run () {
        boolean result = !_redoStack.empty();
        if (result) {
          // TODO
          UndoableCommand cmd = _redoStack.pop();
          cmd.redo();
          _undoStack.push(cmd);

        }
        return result;
      }
    };

  /**
   * Add a command to the undo stack, and clear the redo stack.
   * 
   * @param cmd The command to add to the undo stack.
   */
  public void add(UndoableCommand cmd) {
    // TODO
    _undoStack.push(cmd);
    _redoStack.clear();
  }
  
  /**
   * If the undo command is null, then return null, otherwise return the undo command.
   * 
   * @return The undo command.
   */
  public RerunnableCommand getUndo() {
    return _undoCmd;
  }
  
  /**
   * Return the command that will redo the last undo.
   * 
   * @return The redo command.
   */
  public RerunnableCommand getRedo() {
    return _redoCmd;
  }
  
  // For testing
  UndoableCommand topUndoCommand() {
    if (_undoStack.empty())
      return null;
    else
      return _undoStack.peek();
  }
  // For testing
  UndoableCommand topRedoCommand() {
    if (_redoStack.empty())
      return null;
    else
      return _redoStack.peek();
  }
}
