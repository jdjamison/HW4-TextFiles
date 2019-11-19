// COURSE: CSCI1620
// TERM: Fall 2019
//
// NAME: Joe Jamison
// RESOURCES: Only the JavaDoc was used on this assignment.
package reports;

/**
 * An Exception to be thrown if it is attempted to write a report that has not been processed.
 * The "serialVersionUID" warning can be suppressed.
 * @author jdjamison
 *
 */
public class DataNotProcessedException extends Exception 
{	
	private static final long serialVersionUID = 1;
	/**
	 * Sets the message of the Exception to "Data not processed, cannot write report".
	 */
	public DataNotProcessedException()
	{
		super("Data not processed, cannot write report");
	}
}
