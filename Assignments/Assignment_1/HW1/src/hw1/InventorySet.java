package hw1;

import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.ArrayList;

// TODO: complete the methods
/**
 * An Inventory implemented using a <code>HashMap&lt;Video,Record&gt;</code>.
 * Keys are Videos; Values are Records.
 *
 * @objecttype Mutable Collection of Records
 * @objectinvariant
 *   Every key and value in the map is non-<code>null</code>.
 * @objectinvariant
 *   Each value <code>r</code> is stored under key <code>r.video</code>.
 */
final class InventorySet {
  /** @invariant <code>_data != null</code> */
  private final Map<VideoObj,Record> _data = new HashMap<VideoObj,Record>();

  InventorySet() { }

  /**
   * Return the number of Records.
   */
  public int size() {
    // TODO
    System.out.println(this.toString()+ this._data.size());
    return this._data.size();
  }

  /**
   *  Return a copy of the record for a given Video; if not present, return <code>null</code>.
   */
  public Record get(VideoObj v) {
    // TODO
    return this._data.get(v).copy();
  }

  /**
   * Return a copy of the records as a collection.
   * Neither the underlying collection, nor the actual records are returned.
   */
  public Collection toCollection() {
    // Recall that an ArrayList is a Collection.
    // TODO
    Collection<Record> collection = new ArrayList<Record>();
    for (Record r : this._data.values())
      collection.add(r.copy());
    return collection;
  }

  /**
   * Add or remove copies of a video from the inventory.
   * If a video record is not already present (and change is
   * positive), a record is created. 
   * If a record is already present, <code>numOwned</code> is
   * modified using <code>change</code>.
   * If <code>change</code> brings the number of copies to be less
   * than one, the record is removed from the inventory.
   * @param video the video to be added.
   * @param change the number of copies to add (or remove if negative).
   * @throws IllegalArgumentException if video null or change is zero
   * @postcondition changes the record for the video
   */
  public void addNumOwned(VideoObj video, int change) {
    // TODO  
    System.out.println(this._data.containsKey(video));
    if (video == null || change == 0 || (!this._data.containsKey(video) && change < 0))
      throw new IllegalArgumentException();
    if (!this._data.containsKey(video) && 0 < change)
      this._data.put(video, new Record(video, change, 0, 0));
    else {
      this._data.get(video).numOwned += change;
      if (this._data.get(video).numOwned  < 1)
        this._data.remove(video);
    }
  }

  /**
   * Check out a video.
   * @param video the video to be checked out.
   * @throws IllegalArgumentException if video has no record or numOut
   * equals numOwned.
   * @postcondition changes the record for the video
   */
  public void checkOut(VideoObj video) {
    // TODO
    if (!this._data.containsKey(video) || this._data.get(video).numOut == this._data.get(video).numOwned)
      throw new IllegalArgumentException();
    
    this._data.get(video).numOut++;
    this._data.get(video).numRentals++;
  }
  
  /**
   * Check in a video.
   * @param video the video to be checked in.
   * @throws IllegalArgumentException if video has no record or numOut
   * non-positive.
   * @postcondition changes the record for the video
   */
  public void checkIn(VideoObj video) {
    // TODO
    if (!this._data.containsKey(video) || this._data.get(video).numOut < 1)
      throw new IllegalArgumentException();

    this._data.get(video).numOut--;
  }
  
  /**
   * Remove all records from the inventory.
   * @postcondition <code>size() == 0</code>
   */
  public void clear() {
    // TODO
    this._data.clear();
  }

  /**
   * Return the contents of the inventory as a string.
   */
  public String toString() {
    StringBuilder buffer = new StringBuilder();
    buffer.append("Database:\n");
    for (Record r : _data.values()) {
      buffer.append("  ");
      buffer.append(r);
      buffer.append("\n");
    }
    return buffer.toString();
  }
}
