// COURSE: CSCI1620
// TERM: Fall 2019
//
// NAME: Joe Jamison
// RESOURCES: Only the JavaDoc was used on this assignment.
package reports;

import analytics.Data;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A report for a single company of Fortune 500 data. Report includes the minimum, 
 * maximum, average, and standard deviation of revenues, profits, and rank for all years 
 * in which the company was ranked in the Fortune 500.
 * @author jdjamison
 *
 */
public class CompanyReport implements Report
{
	/**
	 * String to hold the name of the company for the report.
	 */
	private String companyName;
	/**
	 * double to hold the minimum revenue.
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
	 * Double to hold the minimum rank.
	 */
	private Integer minRank;
	/**
	 * Double to hold the maximum rank.
	 */
	private Integer maxRank;
	/**
	 * Double to hold the average rank.
	 */
	private Double aveRank;
	/**
	 * Double to hold the standard deviation of the rank.
	 */
	private Double sdRank;
	/**
	 * Double to hold the number of times a company has been ranked.
	 */
	private int rankings;
	/**
	 * File to hold the file that is passed in when the constructor is called.
	 */
	private File report;
	/**
	 * boolean that states weather or not the file has been processed.
	 */
	private boolean processed = false;
	
	/**Creates new CompanyReport for given company; data to be read from given file.
	 * @param inputFileIn - File containing Fortune 500 data for this report.
	 * @param companyIn - Company to report Fortune 500 data.
	 */
	public CompanyReport(File inputFileIn, String companyIn)
	{
		companyName = companyIn;
		report = inputFileIn;
	}

	/**
	 * Reads data from Fortune 500 data file; processes the data. The file is a csv file and can be assumed 
	 * is formatted correctly. See supplemental document for details on reading from csv files. Calculates the
	 * minimum, maximum, average, and standard deviation of revenues, profits, and rank for all years the company
	 * is ranked using tools in the Data class.
	 * @return true if processing successful, false if the input file does not exist.
	 */
	public boolean processReport() 
	{
		ArrayList<Double> rank = new ArrayList<Double>();
		ArrayList<Double> profit = new ArrayList<Double>();
		ArrayList<Double> revenue = new ArrayList<Double>();
		
		try 
		{
			Scanner in = new Scanner(report);
			in.nextLine();
			while (in.hasNextLine())
			{
				//1955,1,General Motors,9823.5,806
				String line = in.nextLine();
				Scanner parser = new Scanner(line);
				parser.useDelimiter(",");
				
				parser.next();
				Double currentRank = parser.nextDouble(); 
				String name = parser.next();
				double currentRevenue = parser.nextDouble();
				double currentProfit = parser.nextDouble();
				
				if (name.equals(companyName))
				{
					rank.add(currentRank);
					profit.add(currentProfit);
					revenue.add(currentRevenue);
				}
				if (!in.hasNextLine())
				{
					parser.close();
				}
			}
			Double[] rankArr = new Double[rank.size()];
			rankArr = rank.toArray(rankArr);
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
			//Rank calculations
			minRank = (int) Data.minimum(rankArr).doubleValue();
			maxRank = (int) Data.maximum(rankArr).doubleValue();
			aveRank = Data.average(rankArr);
			sdRank = Data.standardDeviation(rankArr);
			rankings = rankArr.length;
			
			processed = true;
			return true;
			
		}
		catch (FileNotFoundException fnfe)
		{
			return false;
		}
	}

	/**
	 * Writes the processed report to the given file. The given file's contents will look like the result of 
	 * calling CompanyReport's toString.
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
	 * Fortune 500 Report for COMPANY ranked RANKED times
		Revenue
		
		Min: MINREV Max: MAXREV Avg: AVGREV StD: STDREV
		
		Profit
		
		Min: MINPRO Max: MAXPRO Avg: AVGPRO StD: STDPRO
		
		Rank
		
		Min: MINRANK Max: MAXRANK Avg: AVGRANK StD: STDRANK

	 * @return the formatted string containing information regarding the rank, profits, and revenue.
	 */
	public String toString()
	{
		/*String returnString = "Fortune 500 Report for " + companyName + " ranked " + rankings + " times\n"
				+ "Revenue\n" + "Min: " + minRev + " Max: " + maxRev + " Avg: " + aveRev + " StD: " 
				+ sdRev + "\nProfit\n" + "Min: " + minProf + " Max: " + maxProf + " Avg: " + aveProf 
				+ " StD: " + sdProf + "\nRank\n" + "Min: " + minRank + " Max: " + maxRank + " Avg: " 
				+ aveRank + " StD: " + sdRank;*/
		String returnString = String.format("Fortune 500 Report for %s ranked %d times\n" 
				+ "Revenue\n" 
				+ "Min: %.3f Max: %.3f Avg: %.3f StD: %.3f\n" 
				+ "Profit\n" 
				+ "Min: %.3f Max: %.3f Avg: %.3f StD: %.3f\n" 
				+ "Rank\n" 
				+ "Min: %d Max: %d Avg: %.3f StD: %.3f", companyName, rankings, minRev, maxRev, aveRev,
				sdRev, minProf, maxProf, aveProf, sdProf, minRank, maxRank, aveRank, sdRank);
		return returnString;
		
	}
	
	/**
	 * Returns the company of this report.
	 * @return the company of this report.
	 */
	public String getCompany()
	{
		return companyName;
	}
	
}
