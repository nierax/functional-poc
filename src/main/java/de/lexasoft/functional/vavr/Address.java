/**
 * 
 */
package de.lexasoft.functional.vavr;

/**
 * 
 */
public class Address {

  public enum Countries {
    GERMAN, AUSTRIA, FRANCE, SWITZERLAND, DANMARK
  }

  public final String road;
  public final String nr;
  public final String zip;
  public final String city;
  public final Countries country;

  private Address(String road, String nr, String zip, String city, Countries country) {
    this.road = road;
    this.nr = nr;
    this.zip = zip;
    this.city = city;
    this.country = country;
  }

  public final static Address of(String road, String nr, String zip, String city, Countries country) {
    return new Address(road, nr, zip, city, country);
  }

}
