// COURSE: CSCI1620
// TERM: Fall 2019
//
// NAME: Joe Jamison
// RESOURCES: Only the JavaDoc was used on this assignment.

package reports;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import analytics.Data;

/**
 * A report for a single year of Fortune 500 data. Report includes the minimum, maximum, average, 
 * and standard deviation of revenues and profits for all ranked companies of the report's year.
 * @author jdjamison
 *
 */
public class YearReport implements Report
{
	/**
	 * File to hold the file that is passed in when the constructor is called.
	 */
	private File report;
	/**
	 * The year to search the file for.
	 */
	private int year;
	/**
	 * Double to hold the minimum revenue.
	 */
	private Double minRev;
	/**
	 * Double to hold the maximum revenue.
	 */
	private Double maxRev;
	/**
	 * Double to hold the average revenue.
	 */
	private Double aveRev;
	/**
	 * Double to hold the standard deviation of the revenue.
	 */
	private Double sdRev;
	
	/**
	 * Double to hold the minimum profit.
	 */
	private Double minProf;
	/**
	 * Double to hold the maximum profit.
	 */
	private Double maxProf;
	/**
	 * Double to hold the average profit.
	 */
	private Double aveProf;
	/**
	 * Double to hold the standard deviation of the profit.
	 */
	private Double sdProf;
	/**
	 * boolean that states weather or not the file has been processed.
	 */
	private boolean processed;
	/**
	 * Creates new YearReport for given year; data to be read from given file.
	 * @param inputFileIn - File containing Fortune 500 data for this report.
	 * @param yearIn - Year to report Fortune 500 data.
	 */
	public YearReport(File inputFileIn, int yearIn)
	{
		report = inputFileIn;
		year = yearIn;
	}

	/**
	 * Reads data from Fortune 500 data file; processes the data. The file is a csv file and can be assumed
	 * is formatted correctly. See supplemental document for details on reading from csv files.Calculates the
	 *  minimum, maximum, average, and standard deviation of revenues and profits for all ranked companies of 
	 *  the report's year using tools in the Data class.
	 *  @return true if processing successful, false if the input file does not exist.
	 *  @throws YearNotFoundException - Thrown if the report's year is not present in the data file.
	 */
	public boolean processReport() throws YearNotFoundException
	{
		ArrayList<Double> profit = new ArrayList<Double>();
		ArrayList<Double> revenue = new ArrayList<Double>();
		boolean returnVal;
		
		try 
		{
			if (year < MINYEAR || year > MAXYEAR)
			{
				throw new YearNotFoundException();
			}
			Scanner in = new Scanner(report);
			in.nextLine();
			while (in.hasNextLine())
			{
				//1955,1,General Motors,9823.5,806
				String line = in.nextLine();
				Scanner parser = new Scanner(line);
				parser.useDelimiter(",");
				
				Integer searchYear = parser.nextInt();
				parser.nextInt(); 
				parser.next();
				Double currentRevenue = parser.nextDouble();
				Double currentProfit = parser.nextDouble();
				
				
				if (searchYear == year)
				{
					profit.add(currentProfit);
					revenue.add(currentRevenue);
				}
				if (!in.hasNextLine())
				{
					parser.close();
				}
			}
			Double[] revenueArr = new Double[revenue.size()]; 
			revenueArr = revenue.toArray(revenueArr);
			Double[] profitArr = new Double[profit.size()];
			profitArr = profit.toArray(profitArr);
			in.close();
			
			//Revenue calculations
			minRev = Data.minimum(revenueArr);
			maxRev = Data.maximum(revenueArr);
			aveRev = Data.average(revenueArr);
			sdRev = Data.standardDeviation(revenueArr);
			//Profit calculations
			minProf = Data.minimum(profitArr);
			maxProf = Data.maximum(profitArr);
			aveProf = Data.average(profitArr);
			sdProf = Data.standardDeviation(profitArr);
			returnVal = true;
			processed = true;
			
		}
		catch (FileNotFoundException fnfe)
		{
			returnVal = false;
		}
		return returnVal;
	}

	/**
	 * Writes the processed report to the given file. The given file's contents will look like the result of
	 * calling YearReport's toString.
	 * @param outputFile - File to write report to.
	 * @return true if write successful, false if file cannot be created.
	 * @throws DataNotProcessedException - Thrown if write attempted and report has not yet been processed.
	 */
	public boolean writeReport(File outputFile) throws DataNotProcessedException 
	{
		boolean returnVal = false;
		try 
		{
			if (processed)
			{
				String filename = outputFile.toString();
				FileOutputStream fileOut = new FileOutputStream(filename, false);
				PrintWriter writer = new PrintWriter(fileOut);
				writer.print(this.toString());
				returnVal = true;
				writer.close();
			}
			else
			{
				throw new DataNotProcessedException();
			}
		}
		catch (FileNotFoundException fnfe)
		{
			returnVal = false;
		}
		return returnVal;
	}
	/**
	 * Returns a formatted String of this report suitable for writing to an output file.
	 * @return the formatted string.
	 */
	public String toString()
	{
		/*String returnString = "Fortune 500 Report for " + year + "\nRevenue\n" + "Min: " + minRev + " Max: " 
				+ maxRev + " Avg: " + aveRev + " StD: " + sdRev + "\nProfit\n" + "Min: " + minProf 
				+ " Max: " + maxProf + " Avg: " + aveProf + " StD: " + sdProf; */
		String returnString2 = "";
		returnString2 = String.format("Fortune 500 Report for %d\n" 
				+ "Revenue\n" 
				+ "Min: %.3f Max: %.3f Avg: %.3f StD: %.3f\n" 
				+ "Profit\n" 
				+ "Min: %.3f Max: %.3f Avg: %.3f StD: %.3f", year, minRev, maxRev, aveRev, sdRev, 
				minProf, maxProf, aveProf, sdProf);
		return returnString2;
		
	}
	/**
	 * Gets the year that the report is being searched for.
	 * @return the searching year.
	 */
	public int getYear()
	{
		return year;
	}

}
