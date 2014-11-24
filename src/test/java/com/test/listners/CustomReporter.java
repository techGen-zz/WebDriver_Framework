package com.test.listners;

import java.util.List;

import com.test.reporting.*;

import org.uncommons.reportng.HTMLReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;

public class CustomReporter extends HTMLReporter {

	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
			String outputDirectoryName) {
		super.generateReport(xmlSuites, suites, outputDirectoryName);

		try {
			SendEmail.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
