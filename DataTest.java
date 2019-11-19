package tests;

import static org.junit.Assert.*;
import java.io.File;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import analytics.Data;
import reports.CompanyReport;
import reports.DataNotProcessedException;

public class DataTest 
{
	private static final double TOLERANCE = 0.001;
	
	@Test
	public void testStandardDeviation()
	{
		Double[] test = {1.0, 2.0, 3.0, 4.0};
		double sd = Data.standardDeviation(test);
		assertEquals(sd, 1.118033988749895, TOLERANCE);
	}
	@Test
	public void testAverage()
	{
		Double[] test = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0};
		double ave = Data.average(test);
		assertEquals(ave, 3.5, TOLERANCE);
	}
	@Test
	public void testMax()
	{
		Double[] test = {1.0,11.0, 2.0, 3.0, 4.0, 9.0, 5.0, 6.0};
		double max = Data.maximum(test);
		assertEquals(max, 11.0, TOLERANCE);
	}
	@Test
	public void testMin()
	{
		Double[] test = {1.0,11.0, 2.0, 3.0, 4.0, 9.0, 5.0, 6.0};
		double min = Data.minimum(test);
		assertEquals(min, 1.0, TOLERANCE);
	}
	@SuppressWarnings("static-access")
	@Test
	public void testConstructor()
	{
		Double[] testArr = {1.0, 3.0};
		Data test = new Data();
		assertEquals(test.maximum(testArr), 3.0, TOLERANCE);
	}
}
