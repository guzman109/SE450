package shop.data;

import shop.command.RerunnableCommand;
import shop.command.UndoableCommand;

import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;
/**
 * A static class for accessing data objects.
 */
public class Data {
  // private static WeakHashMap<Integer,Video> _videos = new WeakHashMap<Integer, Video>();
  private static WeakHashMap<Video, Video> _videos = new WeakHashMap<Video, Video>(); 
  private Data() {}
  /**
   * Returns a new Inventory.
   */
  static public final Inventory newInventory() {
    return new InventorySet();
  }

  /**
   * Factory method for Video objects.
   * Title and director are "trimmed" to remove leading and final space.
   * @throws IllegalArgumentException if Video invariant violated.
   */
  static public Video newVideo(String title, int year, String director) {
    // TODO
    Video v = new VideoObj(title, year, director);
    Video k = _videos.get(v);
    if (   (k != null))
      v = k;
    else
      _videos.put(v, v);
    return v;
  }

  /**
   * Returns a command to add or remove copies of a video from the inventory.
   * <p>The returned command has the following behavior:</p>
   * <ul>
   * <li>If a video record is not already present (and change is
   * positive), a record is created.</li>
   * <li>If a record is already present, <code>numOwned</code> is
   * modified using <code>change</code>.</li>
   * <li>If <code>change</code> brings the number of copies to be less
   * than one, the record is removed from the inventory.</li>
   * </ul>
   * @param video the video to be added.
   * @param change the number of copies to add (or remove if negative).
   * @throws IllegalArgumentException if <code>inventory<code> not created by a call to <code>newInventory</code>.
   */
  static public UndoableCommand newAddCmd(Inventory inventory, Video video, int change) {
    if (!(inventory instanceof InventorySet))
      throw new IllegalArgumentException();
    return new CmdAdd((InventorySet) inventory, video, change);
  }

  /**
   * Returns a command to check out a video.
   * @param video the video to be checked out.
   */
  static public UndoableCommand newOutCmd(Inventory inventory, Video video) {
    // TODO
    if ( !(inventory instanceof InventorySet) )
      throw new IllegalArgumentException();
    return new CmdOut((InventorySet) inventory, video);
  }
  
  /**
   * Returns a command to check in a video.
   * @param video the video to be checked in.
   */
  static public UndoableCommand newInCmd(Inventory inventory, Video video) {
    // TODO
    if ( !(inventory instanceof InventorySet) )
      throw new IllegalArgumentException();
    return new CmdIn((InventorySet) inventory, video);
  }
  

  /**
   * If the inventory is an InventorySet, then return a new CmdClear object that takes an InventorySet as
   * a parameter.
     * Returns a command to remove all records from the inventory.

  * @param inventory the inventory to clear
  * @return A new instance of the CmdClear class.
  */
  static public UndoableCommand newClearCmd(Inventory inventory) {
    if (!(inventory instanceof InventorySet))
      throw new IllegalArgumentException();
    return new CmdClear((InventorySet) inventory);
  }


  /**
   * If the inventory is an InventorySet, then return the undo command from its history.
   * Returns a command to undo that will undo the last successful UndoableCommand. 
   * @param inventory the inventory object
   * @return A command object that can be executed to undo the last command.
   */
  static public RerunnableCommand newUndoCmd(Inventory inventory) {
    if (!(inventory instanceof InventorySet))
      throw new IllegalArgumentException();
    return ((InventorySet)inventory).getHistory().getUndo();
  }

  /**
   * "If the inventory is an InventorySet, return the redo command from its history."
   * 
   * The first line of the function is a comment. Comments are ignored by the compiler. They are used to
   * document the code
   *    
   * Returns a command to redo that last successfully undone command. 
   * @param inventory the inventory to be used
   * @return A command that can be executed to redo the last undone command.
   */
  static public RerunnableCommand newRedoCmd(Inventory inventory) {
    // TODO
    if (!(inventory instanceof InventorySet))
      throw new IllegalArgumentException();
    return ((InventorySet)inventory).getHistory().getRedo();
  }
}  
