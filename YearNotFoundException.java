// COURSE: CSCI1620
// TERM: Fall 2019
//
// NAME: Joe Jamison
// RESOURCES: Only the JavaDoc was used on this assignment.
package reports;
/**
 * An Exception to be thrown if a requested year is not present in the data set. The "serialVersionUID" 
 * warning can be suppressed.
 * @author jdjamison
 *
 */
public class YearNotFoundException extends RuntimeException
{
	private static final long serialVersionUID = 1;
	/**
	 * Sets the message of the Exception to Sets the message of the Exception to "Requested year not in file".
	 */
	public YearNotFoundException()
	{
		super("Requested year not in file");
	}
}
