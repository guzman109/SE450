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

  public String director() {
    // TODO
    return this._director;
  }

  public String title() {
    // TODO
    return this._title;
  }

  public int year() {
    // TODO
    return this._year;
  }

  public boolean equals(Object thatObject) {
    // TODO
    if (!(thatObject instanceof VideoObj))
      return false;
    VideoObj tempObj = (VideoObj) thatObject;
    return this._title.equals(tempObj.title()) && 
      (this._year == tempObj.year()) && 
      this._director.equals(tempObj.director());
  }

  public int hashCode() {
    // TODO
    int result = 17;
    int c = this._title.hashCode();
    result = 37 * result + c;

    c = Integer.hashCode(this._year);
    result = 37 * result + c;

    c = this._director.hashCode();
    result = 37* result + c;
    return result;
  }

  public int compareTo(Video that) {
    // TODO
    Integer year = this._year;
    return this._title.compareTo(that.title()) + 
      year.compareTo(that.year()) + 
        this._director.compareTo(that.director());
  }

  public String toString() {
    // TODO
    return String.format("%s (%d) : %s", this._title, this._year, this._director);
  }
}
