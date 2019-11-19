// COURSE: CSCI1620
// TERM: Fall 2019
//
// NAME: Joe Jamison
// RESOURCES: Only the JavaDoc was used on this assignment.
package analytics;
/**
 * Set of useful reusable tools for analyzing sets of data.
 * @author jdjamison
 *
 */
public class Data 
{
	/**
	 * Finds the average of values in the passed array.
	 * @param <N> - Type of data passed. The type must extend the Number class. 
	 * See the assignment supplemental for useful information on the Number class.
	 * @param data - Collection of data to find average of.
	 * @return Average of passed data. Regardless of type passed will always return a Double.
	 * This means if no results are in data it will return Double's "Divide By 0" value,
	 * NaN, which it should do automatically.
	 */
	public static <N extends Number> Double average(N[] data)
	{
		double sum = 0.0;
		double counter = 0.0;
		for (int i = 0; i < data.length; i++)
		{
			if (data[i] != null)
			{
				sum += data[i].doubleValue();
				counter++;
			}
			else
			{
				break;
			}
		}
		return sum / counter;
	}
	
	/**
	 * Finds the minimum value in the passed array. NOTE: The array does not need to be completely populated.
	 * All relevant data to be processed starts at index 0. Unused elements will be null and should not be
	 * considered in computations. Once the first null is encountered it is assumed remaining elements are unused.
	 * @param <E> - Type of data passed. The type must implement the Comparable interface.
	 * @param data - Collection of data to find minimum value of.
	 * @return Minimum value in passed data.
	 */
	public static <E extends Comparable<E>> E minimum(E[] data)
	{
		E minimum;
		if (data.length == 0)
		{
			minimum = null;
		}
		else
		{
			minimum = data[0];
			for (int i = 0; i < data.length; i++)
			{
				if (data[i].compareTo(minimum) < 0)
				{
					minimum = data[i];
				}
			}
		}
		return minimum;
	}
	
	/**
	 * Finds the maximum value in the passed array. NOTE: The array does not need to be completely populated.
	 * All relevant data to be processed starts at index 0. Unused elements will be null and should not be
	 * considered in computations. Once the first null is encountered it is assumed remaining elements are unused.
	 * @param <E> - Type of data passed. The type must implement the Comparable interface.
	 * @param data - Collection of data to find maximum value of.
	 * @return Maximum value in passed data.
	 */
	public static <E extends Comparable<E>> E maximum(E[] data)
	{
		E maximum;
		if (data.length == 0)
		{
			maximum = null;
		}
		else
		{
			maximum = data[0];
			for (int i = 0; i < data.length; i++)
			{
				if (data[i].compareTo(maximum) > 0)
				{
					maximum = data[i];
				}
			}
		}
		return maximum;
	}
	
	/**
	 * Finds the population standard deviation of values in the passed array.
	 * NOTE: The array does not need to be completely populated. All relevant data to be processed starts at 
	 * index 0. Unused elements will be null and should not be considered in computations. Once the first null 
	 * is encountered it is assumed remaining elements are unused.
	 * @param <N> Type of data passed. The type must extend the Number class. See the assignment supplemental for 
	 * useful information on the Number class.
	 * @param data - Collection of data to find standard deviation of.
	 * @return Population Standard Deviation of passed data. Regardless of type passed will always return a Double.
	 * This means if no results are in data it will return Double's "Divide By 0" value, NaN, which it should do 
	 * automatically.
	 */
	public static <N extends Number> Double standardDeviation(N[] data)
	{
		int counter = 0;
		double deviation = 0.0;
		double average = average(data);
		for (int i = 0; i < data.length; i++)
		{
			if (data[i] == null)
			{
				break;
			}
			deviation += (data[i].doubleValue() - average) * (data[i].doubleValue() - average);
			counter++;
		}
		deviation = deviation / counter;
		deviation = Math.sqrt(deviation);
		return deviation;
		
	}

}
