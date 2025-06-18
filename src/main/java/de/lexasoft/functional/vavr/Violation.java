/**
 * 
 */
package de.lexasoft.functional.vavr;

/**
 * 
 */
public class Violation {

  public enum Severity {
    FATAL, ERROR, INFO
  }

  public final String id;
  public final String message;
  public final Severity severity;

  private Violation(String id, String message, Severity severity) {
    this.id = id;
    this.message = message;
    this.severity = severity;
  }

  public final static Violation of(String id, String message, Severity severity) {
    return new Violation(id, message, severity);
  }

}
