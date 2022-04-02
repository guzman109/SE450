package hw1;

// TODO: complete the methods
/**
 * Immutable Data Class for video objects.
 * Comprises a triple: title, year, director.
 *
 * @objecttype Immutable Data Class
 * @objectinvariant
 *   Title is non-null, no leading or final spaces, not empty string.
 * @objectinvariant
 *   Year is greater than 1800, less than 5000.
 * @objectinvariant
 *   Director is non-null, no leading or final spaces, not empty string.
 */
final class VideoObj implements Comparable<VideoObj> {
  /** @invariant non-null, no leading or final spaces, not empty string */
  private final String _title;
  /** @invariant greater than 1800, less than 5000 */
  private final int    _year;
  /** @invariant non-null, no leading or final spaces, not empty string */
  private final String _director;

  /**
   * Initialize all object attributes.
   * Title and director are "trimmed" to remove leading and final space.
   * @throws IllegalArgumentException if any object invariant is violated.
   */
  VideoObj(String title, int year, String director) {
    // TODO
    if (title.isBlank() || title.startsWith(" ") || title.endsWith(" ") || title == null)
      throw new IllegalArgumentException();
    else if (director.isBlank() || director.startsWith(" ") || director.endsWith(" ") || director == null)
      throw new IllegalArgumentException();
    else if (year < 1800 || year > 5000)
      throw new IllegalArgumentException();
    this._title = title;
    this._year = year;
    this._director = director;
     
  }

  /**
   * Return the value of the attribute.
   */
  public String director() {
    // TODO
    return this._director;
  }

  /**
   * Return the value of the attribute.
   */
  public String title() {
    // TODO
    return this._title;
  }

  /**
   * Return the value of the attribute.
   */
  public int year() {
    // TODO
    return this._year;
  }

  /**
   * Compare the attributes of this object with those of thatObject.
   * @param thatObject the Object to be compared.
   * @return deep equality test between this and thatObject.
   */
  public boolean equals(Object thatObject) {
    // TODO
    boolean is_equal = true;

    if (this._title.equals(((VideoObj)thatObject).title()))
      is_equal = false;
    if (this._year == ((VideoObj)thatObject).year())
      is_equal = false;
    if (this._director.equals(((VideoObj)thatObject).director()))
      is_equal = false;
    
    return is_equal;
  }

  /**
   * Return a hash code value for this object using the algorithm from Bloch:
   * fields are added in the following order: title, year, director.
   */
  public int hashCode() {
    // TODO
    return 17 + this._title.hashCode() + Integer.hashCode(this._year) + this._director.hashCode();
  }

  /**
   * Compares the attributes of this object with those of thatObject, in
   * the following order: title, year, director.
   * @param that the VideoObj to be compared.
   * @return a negative integer, zero, or a positive integer as this
   *  object is less than, equal to, or greater than that object.
   */
  public int compareTo(VideoObj that) {
    // TODO
  Integer year = this._year;
    return this._title.compareTo(that.title()) + year.compareTo(that.year()) + this._director.compareTo(that.director());
  }

  /**
   * Return a string representation of the object in the following format:
   * <code>"title (year) : director"</code>.
   */
  public String toString() {
    // TODO
    // return "El Mariachi (1996) : Rodriguez";
    return String.format("%s (%d) : %s", this._title, this._year, this._director);
  }
}
