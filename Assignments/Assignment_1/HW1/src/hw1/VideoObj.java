/*
  Carlos Guzman
  SE450
*/
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
public final class VideoObj implements Comparable<VideoObj> {
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
    if (title == null || title.isBlank())
      throw new IllegalArgumentException("Title is null, has leading or trailing white space, or is an empty string");
    else if (director == null || director.isBlank())
      throw new IllegalArgumentException("Director is null, has leading or trailing white space, or is an empty string");
    else if (year <= 1800 || year >= 5000)
      throw new IllegalArgumentException("Year is less than 1800 or greater than 5000");

    this._title = title.strip();
    this._year = year;
    this._director = director.strip();
     
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
    if (thatObject == null || !(thatObject instanceof VideoObj))
      is_equal = false;
    else {
      VideoObj tempObject = VideoObj.class.cast(thatObject);

      if (!this._title.equals(tempObject.title()))
        is_equal = false;
      if (this._year != tempObject.year())
        is_equal = false;
      if (!this._director.equals(tempObject.director()))
        is_equal = false;
    }
    return is_equal;
  }

  /**
   * Return a hash code value for this object using the algorithm from Bloch:
   * fields are added in the following order: title, year, director.
   */
  public int hashCode() {
    // TODO
    int result = 17;
    int c = this._title.hashCode();
    result = 37 * result + c;

    c =  Integer.hashCode(this._year);
    result = 37 * result + c;

    c =  this._director.hashCode();
    result = 37 * result + c;
    return result;
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
