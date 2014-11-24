package com.test.excelsheet;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

public class ReadExcelAnnotation {

	public static Map<String, String> extractAnnotationValue(Method method) {
		ReadExcelSheet fullAnnotation = method
				.getAnnotation(ReadExcelSheet.class);
		String filePath = null;
		String fileName = null;
		String sheetName = null;

		if ((fullAnnotation instanceof ReadExcelSheet)) {
			filePath = fullAnnotation.filePath();
			fileName = fullAnnotation.fileName();
			sheetName = fullAnnotation.sheetName();
		} else {
			System.out.println("Something wrong in Excelshet Annotation in "
					+ method.getName() + " method");
		}
		Map<String, String> map = new TreeMap<String, String>();
		map.put("filePath", filePath);
		map.put("fileName", fileName);
		map.put("sheetName", sheetName);
		return map;
	}
}
