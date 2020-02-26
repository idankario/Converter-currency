package converter;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public interface HandlingXmlInterface {

	ArrayList<Currencie> getCurrencyList();

	void setCurrencyList1(ArrayList<Currencie> currencyList);

	void conectXmlFile();

	NodeList getListElement(URL url, String elmentName) throws IOException, ParserConfigurationException, SAXException;

	NodeList getListElement(String file, String elmentName)
			throws IOException, ParserConfigurationException, SAXException;

	void addCurrency(int i, NodeList curNodeList);

	String getInfoCur(int currency, int label, NodeList curNodeList);

	void saveUrl() throws MalformedURLException, IOException;

	String[] getXmlFileName();

	void setXml(String[] xml);

	boolean isUpdate();

	String getLastUpdate();

}