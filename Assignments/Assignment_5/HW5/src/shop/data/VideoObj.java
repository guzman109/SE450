package shop.data;

/**
 * Implementation of Video interface.
 * @see Data
 */
final class VideoObj implements Video {
  private final String _title;
  private final int    _year;
  private final String _director;

  /**
   * Initialize all object attributes.
   * Title and director are "trimmed" to remove leading and final space.
   * @throws IllegalArgumentException if object invariant violated.
   */
  VideoObj(String title, int year, String director) {
    if (title == null || title.isBlank())
      throw new IllegalArgumentException("Title is null, has leading or trailing white space, or is an empty string");
    else if (director == null || director.isBlank())
      throw new IllegalArgumentException("Director is null, has leading or trailing white space, or is an empty string");
    else if (year <= 1800 || year >= 5000)
      throw new IllegalArgumentException("Year is less than 1800 or greater than 5000");
    _title = title.strip();
    _director = director.strip();
    _year = year;
  }

/**
 * Return the director of the movie.
 * 
 * @return The director of the movie.
 */
  public String director() {
    // TODO
    return this._director;
  }

/**
 * Return the title of the book.
 * 
 * @return The title of the movie.
 */
  public String title() {
    // TODO
    return this._title;
  }

/**
 * Return the year of this date.
 * 
 * @return The year of the date.
 */
  public int year() {
    // TODO
    return this._year;
  }

/**
 * > If the object passed in is not a VideoObj, return false. Otherwise, return true if the title,
 * year, and director of the two objects are equal
 * 
 * @param thatObject The object that we are comparing to.
 * @return The hashcode of the object.
 */
  public boolean equals(Object thatObject) {
    // TODO
    if ( !(thatObject instanceof VideoObj) )
      return false;
    VideoObj tempObj = (VideoObj) thatObject;
    return this._title.equals(tempObj.title()) &&
      (this._year == tempObj.year()) &&
      this._director.equals(tempObj.director());
  }

/**
 * > The hashCode function returns a number that is the same for two objects if they are equal, and
 * different for two objects if they are not equal
 * 
 * @return The hashCode of the Movie object.
 */
  public int hashCode() {
    // TODO
    int result = 17;
    int c = this._title.hashCode();
    result = 37 * result + c;

    c = Integer.hashCode(this._year);
    result = 37 * result + c;

    c = this._director.hashCode();
    result = 37 * result + c;
    return result;
  }

  public int compareTo(Video that) {
    // TODO
    Integer year = this._year;
    return this._title.compareTo(that.title()) +
      year.compareTo(that.year()) +
      this._director.compareTo(that.director());
  }

/**
 * It returns a string that contains the title, year, and director of the movie
 * 
 * @return The title, year, and director of the movie.
 */
  public String toString() {
    // TODO
    return String.format("%s (%d) : %s", this._title, this._year, this._director);
  }
}
