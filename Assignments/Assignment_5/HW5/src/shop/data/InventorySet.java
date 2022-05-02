package shop.data;

import java.util.Map;
import java.util.HashMap;
import java.util.Comparator;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import shop.command.Command;

/**
 * Implementation of Inventory interface.
 * @see Data
 */
final class InventorySet implements Inventory {
  // Chose to use Map of Record, rather than RecordObj, because of
  // Java's broken generic types.  The story is too sad to retell, but
  // involves the fact that Iterable<? extends Record> is not a valid
  // type, and that Iterator<RecordObj> is not a subtype of
  // Iterator<Record>.
  //
  // Seems like the best approach for Java generics is to use the
  // external representation internally and downcast when necessary.
  private final Map<Video,Record> _data;

  InventorySet() {
    this._data = new HashMap<Video,Record>();
  }

  
  /** Returns the number of videos in inventory.
   * @return int Number of videos in inventory.
   */
  public int size() {
    // TODO
    return this._data.size();
  }

  
  /** Returns the record for video v
   * @param v Video to find the record of 
   * @return Record record for Video v
   */
  public Record get(Video v) {
    // TODO
    return this._data.get(v);
  }

  
  /** Returns an iterator of the records on file.
   * @return Iterator<Record> itertator of the records.
   */
  public Iterator<Record> iterator() {
    return Collections.unmodifiableCollection(this._data.values()).iterator();
  }

  
  /** Returns a sorted iterator of the records on file
   * @param comparator
   * @return Iterator<Record> sorted iterator of the records
   */
  public Iterator<Record> iterator(Comparator<Record> comparator) {
    // TODO(
    List<Record> values = new ArrayList<Record> (this._data.values());
    Collections.sort(values, comparator);
    return Collections.unmodifiableList(values).iterator();
  }



  /** Method to check the invariants for checking out a video.
  * @param video the video to be added.
  * @param change the number of copies to add (or remove if negative).
  * @throws IllegalArgumentException if video null, change is zero, if attempting to remove more copies than are owned, 
  *    or if attempting to remove copies that are checked out.
  */
  private void checkAddNumOwnedPreConditions(Video video, int change) {
    if (video == null)
      throw new IllegalArgumentException("Video is null.");
    if (change == 0)
      throw new IllegalArgumentException("Change is zero.");
    
    if (this._data.containsKey(video)) {
      Record r = this._data.get(video);
      if (r.numOwned() + change < 0)
        throw new IllegalArgumentException("Attempting to remove more copies than are owned.");

      if ( (r.numOwned() - r.numOut() + change) < 0 )
        throw new IllegalArgumentException("Attempting to remove copies that are check out.");
    }
    else {
      if (change < 0)
        throw new IllegalArgumentException("Attempting to remove copies that don't exist.");
    }



  }
  /**
   * Add or remove copies of a video from the inventory.
   * If a video record is not already present (and change is
   * positive), a record is created. 
   * If a record is already present, <code>numOwned</code> is
   * modified using <code>change</code>.
   * If <code>change</code> brings the number of copies to be zero,
   * the record is removed from the inventory.
   * @param video the video to be added.
   * @param change the number of copies to add (or remove if negative).
   * @throws IllegalArgumentException if video null, change is zero, if attempting to remove more copies than are owned, or if attempting to remove copies that are checked out.
   */
  void addNumOwned(Video video, int change) {

    // TODO
    checkAddNumOwnedPreConditions(video, change);

    if (!this._data.containsKey(video) && 0 < change)
      this._data.put(video, new RecordObj(video, change, 0, 0));
    else {
      RecordObj r = (RecordObj) this._data.get(video);
      r.numOwned += change;
      this._data.put(video, r);
      if (r.numOwned()  <= 0)
        this._data.remove(video);

    }
  }

  /**
   * Check out a video.
   * @param video the video to be checked out.
   * @throws IllegalArgumentException if video has no record or numOut
   * equals numOwned.
   */
  void checkOut(Video video) {
    // TODO
    if (!this._data.containsKey(video) || this._data.get(video).numOut() == this._data.get(video).numOwned())
    throw new IllegalArgumentException("Video has no record or number of copies to check out.");
  
    RecordObj r = (RecordObj) this._data.get(video);
    r.numOut++;
    r.numRentals++;
    this._data.put(video, r);
  }
  
  /**
   * Check in a video.
   * @param video the video to be checked in.
   * @throws IllegalArgumentException if video has no record or numOut
   * non-positive.
   */
  void checkIn(Video video) {
    // TODO
    if (!this._data.containsKey(video) || this._data.get(video).numOut() < 1)
    throw new IllegalArgumentException("Video has no record or numOut is not positive.");
    RecordObj r = (RecordObj) this._data.get(video);
    r.numOut--;
    this._data.put(video,r);
  }
  
  /**
   * Remove all records from the inventory.
   */
  void clear() {
    // TODO
    this._data.clear();
  }

  
  /** Returns a string representation of the iventory.
   * @return String string representation
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


  /**
   * Implementation of Record interface.
   *
   * <p>This is a utility class for Inventory.  Fields are mutable and
   * package-private.</p>
   *
   * <p><b>Class Invariant:</b> No two instances may reference the same Video.</p>
   *
   * @see Record
   */
  private static final class RecordObj implements Record {
    Video video; // the video
    int numOwned;   // copies owned
    int numOut;     // copies currently rented
    int numRentals; // total times video has been rented
    
    RecordObj(Video video, int numOwned, int numOut, int numRentals) {
      this.video = video;
      this.numOwned = numOwned;
      this.numOut = numOut;
      this.numRentals = numRentals;
    }
    public Video video() {
      return video;
    }
    public int numOwned() {
      return numOwned;
    }
    public int numOut() {
      return numOut;
    }
    public int numRentals() {
      return numRentals;
    }
    public String toString() {
      StringBuilder buffer = new StringBuilder();
      buffer.append(video);
      buffer.append(" [");
      buffer.append(numOwned);
      buffer.append(",");
      buffer.append(numOut);
      buffer.append(",");
      buffer.append(numRentals);
      buffer.append("]");
      return buffer.toString();
    }
  }
}
