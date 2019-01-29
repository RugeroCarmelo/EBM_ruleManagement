package com.tom.EBM_RuleManager.readXML;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadXML {
	private String fileName;
	private String fileId;
	private InputStream inputStream;
	private File file;

	public ReadXML(String fileName) {
		try {

			File fXmlFile = new File(fileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("decision");
			Node nNode = nList.item(0);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				this.fileName = eElement.getAttribute("name");
				fileId = eElement.getAttribute("id");
			}
			file = fXmlFile;
			inputStream = new FileInputStream(fileName);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getFileName() {
		return fileName;
	}

	public String getFileId() {
		return fileId;
	}
	
	public InputStream getInputStream() {
		return inputStream;
	}
	
	public File getFile(){
		return file;
	}

}
