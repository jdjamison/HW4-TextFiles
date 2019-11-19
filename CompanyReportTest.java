package tests;

import static org.junit.Assert.*;
import java.io.File;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import analytics.Data;
import reports.CompanyReport;
import reports.DataNotProcessedException;

public class CompanyReportTest 
{
	CompanyReport test = new CompanyReport(new File("fortune500.csv"), "Gulf Oil");
	

	@Test
	public void unprocessedToStringTest()
	{
		CompanyReport c = new CompanyReport(new File("fortune500.csv"), "Nike");
		String expected = "Fortune 500 Report for Nike ranked 0 times\n" + 
				"Revenue\n" + 
				"Min: nul Max: nul Avg: nul StD: nul\n" + 
				"Profit\n" + 
				"Min: nul Max: nul Avg: nul StD: nul\n" + 
				"Rank\n" + 
				"Min: null Max: null Avg: nul StD: nul";
		
		assertEquals("Problem in CompanyReport basic toString format, check spelling, capitalization, spacing, and format",
				expected, c.toString());
	}

	@Test
	public void testToString()
	{
		test.processReport();
		String expected = "Fortune 500 Report for Gulf Oil ranked 26 times\n" +
				"Revenue\n" +
				"Min: 182.800 Max: 1322.000 Avg: 528.281 StD: 271.704\n" +
				"Profit\n" +
				"Min: 1705.300 Max: 23910.000 Avg: 7018.458 StD: 6263.850\n" +
				"Rank\n" +
				"Min: 7 Max: 11 Avg: 8.923 StD: 1.357";
		assertEquals(test.toString(), expected); 
	}
	
	@Test
	public void testProcessReport()
	{
		assertTrue(test.processReport());
	}
	
	@Test
	public void testGetCompany()
	{
		assertEquals(test.getCompany(), "Gulf Oil");
	}
	@Test
	public void testFnfException()
	{
		CompanyReport failtest = new CompanyReport(new File("notRealFile"), "Gulf Oil");
		assertFalse(failtest.processReport());

	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testWriteReport() throws DataNotProcessedException
	{
		test.processReport();
		assertTrue(test.writeReport(new File("outputTests.txt")));
	}
	@Test
	public void testNotProcessed() throws DataNotProcessedException
	{
		CompanyReport test2 = new CompanyReport(new File("fortune500.csv"), "Gulf Oil");
		thrown.expect(DataNotProcessedException.class);
		test2.writeReport(new File("outputTests.txt"));
	}
	@Test
	public void testFNFEWriter() throws DataNotProcessedException
	{
		File failuretest = new File("fakeFile");
		failuretest.setReadOnly();
		test.processReport();
		assertFalse(test.writeReport(failuretest));
	}

}
