package com.test.listners;


import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SuiteRun implements ISuiteListener {
	
	com.test.setup.SuiteRun obj = new com.test.setup.SuiteRun();

	@Override
	public void onStart(ISuite suite) {
		String browserName = null;
		try{
			File testngFile = new File("testng.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(testngFile);
			doc.getDocumentElement().normalize();
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			XPathExpression expr = xpath.compile("//suite/parameter[@name]");
			NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
			for(int i =0; i< nl.getLength(); i++){
				Node currentNode = nl.item(i);
				String name = currentNode.getAttributes().getNamedItem("name").getNodeValue();
				if(name.equals("Browser")){
					String browser = currentNode.getAttributes().getNamedItem("value").getNodeValue();
					browserName = browser;
					break;
				}else{
					continue;
				}
			}		
		}catch(Exception e){
			e.printStackTrace();
		}
	
		try {
			obj.initiateWebdriver(browserName);
		} catch (Exception e) {
			e.printStackTrace();
	}
}
	@Override
	public void onFinish(ISuite suite) {
		try {
			obj.terminateWebdriver();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

}
