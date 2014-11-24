package com.test.analyzer;

import org.testng.*;

public class RetryAnalyzer implements IRetryAnalyzer{
	
	int retryCount = 0;
    int retryMaxCount = 2;

	public boolean retry(ITestResult result) {
		if(retryCount<retryMaxCount){
			retryCount++;
			return true;
		}
		return false;
	}
}
