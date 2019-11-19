package tests;

import static org.junit.Assert.*;
import java.io.File;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import reports.CompanyReport;
import reports.DataNotProcessedException;
import reports.YearNotFoundException;
import reports.YearReport;

public class YearReportTest 
{

	@Test
	public void unprocessedToStringTest()
	{
		YearReport y = new YearReport(new File("fortune500.csv"), 2020);
		String expected = "Fortune 500 Report for 2020\n" + 
				"Revenue\n" + 
				"Min: nul Max: nul Avg: nul StD: nul\n" + 
				"Profit\n" + 
				"Min: nul Max: nul Avg: nul StD: nul";
		
		assertEquals("Problem in YearReport basic toString format, check spelling, capitalization, spacing, and format",
				expected, y.toString());
	}

	@Test
	public void testConstructor()
	{
		YearReport test = new YearReport(new File("fortune500.csv"), 2000);
	}
	@Test
	public void testProcessReport()
	{
		YearReport test = new YearReport(new File("fortune500.csv"), 1955);
		assertTrue(test.processReport());
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testWriteReport() throws DataNotProcessedException
	{
		YearReport test = new YearReport(new File("fortune500.csv"), 2000);
		test.processReport();
		assertTrue(test.writeReport(new File("outputTests.txt")));
	}

	@Test
	public void testValidYearProcess()
	{
		YearReport test = new YearReport(new File("fortune500.csv"), 1980);
		assertTrue(test.processReport());
	}
	@Test
	public void testDataNotProcessed() throws DataNotProcessedException
	{
		YearReport test2 = new YearReport(new File("fortune500.csv"), 1955);
		thrown.expect(DataNotProcessedException.class);
		test2.writeReport(new File("outputTests.txt"));
	}
	
	@Test
	public void testFnfException()
	{
		YearReport failtest = new YearReport(new File("notRealFile"), 1989);
		assertFalse(failtest.processReport());

	}
	@Test
	public void testGetYear()
	{
		YearReport test = new YearReport(new File("fortune500.csv"), 1980);
		assertEquals(test.getYear(), 1980);
	}
	@Test
	public void testFNFEWriter() throws DataNotProcessedException
	{
		YearReport test = new YearReport(new File("fortune500.csv"), 1980);
		File failuretest = new File("fakeFile");
		failuretest.setReadOnly();
		test.processReport();
		assertFalse(test.writeReport(failuretest));
	}
	@Test
	public void testYNFException() throws YearNotFoundException
	{
		YearReport test = new YearReport(new File("fortune500.csv"), 1800);
		YearReport test2 = new YearReport(new File("fortune500.csv"), 2050);
		thrown.expect(YearNotFoundException.class);
		test.processReport();
		test2.processReport();
	}
	
}
