package com.tataaig.listener;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import com.tataaig.utilities.TestUtils;

public class AnnotationTransformer implements IAnnotationTransformer {
	int count = 0;

	@SuppressWarnings("rawtypes")
	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		
//		System.out.println("inside AnnotationTransformer transform");
		try {
			if (count == 0) {
//				System.out.println("inside AnnotationTransformer getRunStatus");
				TestUtils.getRunStatus();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (int i = 0; i < TestUtils.testCases.size(); i++) {
			if (testMethod.getName().equalsIgnoreCase(TestUtils.testCases.get(i))) {
				// sets the dataprovider to all the test methods
				annotation.setDataProvider("dataProviderForIterations");
				annotation.setDataProviderClass(TestUtils.class);
				// sets the retry analyser for all the test cases
				annotation.setRetryAnalyzer(RetryFailedTestCases.class);
				// sets the description for all the test cases based on the excel sheet input
				annotation.setDescription(TestUtils.testDescription.get(i));
				if (TestUtils.runStatus.get(i).equalsIgnoreCase("no")) {
					// sets the enabled parameter for all the test cases based on the excel sheet
					// input
					annotation.setEnabled(false);
					break;

				}
			}
		}
		count++;
	}
}
