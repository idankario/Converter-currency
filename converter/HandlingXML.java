package converter;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class HandlingXML implements HandlingXmlInterface 
{
	String[] xml={"https://www.boi.org.il/currency.xml","http://www.bankisrael.gov.il/currency.xml","http://www.abelski.com/currencies.xml","xmlFile/currencies.xml"};
	int indexXml;
	boolean isUpdate;
	String lastUpdate;
	ArrayList<Currencie> currencyList;

	/*
	 * Update the file in backup and store lastUpdate and nodeList of currency
	 */
	HandlingXML()
	{
		indexXml=0;
		isUpdate=false;
		while(isUpdate==false)
		{ 
			conectXmlFile();
			if(indexXml==2)
			{
				System.out.println("could not open web");
				if(isUpdate==false)
				{
					System.out.println("could not open information from xml file :"+xml[indexXml]);
					return;
				}
			}
			else if(isUpdate==false)
			{
				indexXml++;
				System.out.println("could not open information from web:"+xml[indexXml]);
			}
		}
		try 
		{
			saveUrl();
			saveRateCurrency();
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	
	/* (non-Javadoc)
	 * @see converter.HandlingXmlInterface#getCurrencyList()
	 */
	public ArrayList<Currencie> getCurrencyList() 
	{
		return currencyList;
	}

	/* (non-Javadoc)
	 * @see converter.HandlingXmlInterface#setCurrencyList1(java.util.ArrayList)
	 */
	public void setCurrencyList1(ArrayList<Currencie> currencyList) 
	{
		this.currencyList = currencyList;
	}

	/* (non-Javadoc)
	 * @see converter.HandlingXmlInterface#conectXmlFile()
	 */
	public void conectXmlFile() 
	{
		try
		{
			if(indexXml<2)
			{
				lastUpdate=getListElement(new URL(xml[indexXml]),"LAST_UPDATE").item(0).getTextContent();
				NodeList curNodeList=getListElement(new URL(xml[indexXml]),"CURRENCY");
				
				currencyList = new ArrayList<Currencie>();
				currencyList.add(new Currencie("   Shekel" ,"   1", "   ILS","   Israel", "   1","   1"));
				
				for(int i= 0;i < curNodeList.getLength();i++) 
					addCurrency(i,curNodeList);			
				isUpdate=true;
			}
			
			else
			{
				lastUpdate=getListElement(xml[indexXml],"LAST_UPDATE").item(0).getTextContent();
				NodeList curNodeList=getListElement(xml[indexXml],"CURRENCY");
				currencyList.add(new Currencie("   Shekel" ,"   1", "   ILS","   Israel", "   1","   1"));
				System.out.println(curNodeList.getLength());
				for(int i= 0;i < curNodeList.getLength();i++)   
					addCurrency(i,curNodeList);	
				isUpdate=true;
			}
			
		}
		catch (SAXParseException err)
		{
			System.out.println("** Parsing error" + ", line "+ err.getLineNumber() + ", uri "
			                   + err.getSystemId());
			System.out.println(" " + err.getMessage());
		} catch (SAXException e)
		{
			Exception x= e.getException();
			((x == null) ? e : x).printStackTrace();
		} catch (Throwable t)
		{
			t.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see converter.HandlingXmlInterface#getListElement(java.net.URL, java.lang.String)
	 */
	public NodeList getListElement(URL url,String elmentName) throws IOException, ParserConfigurationException, SAXException
	{
	   DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
	   DocumentBuilder builder= factory.newDocumentBuilder();
	   // Document doc= docBuilder.parse(new File("currency.xml"));
	   InputStream input= url.openStream();
	   Document doc= builder.parse(input);
	   doc.getDocumentElement().normalize();
	   return doc.getElementsByTagName(elmentName);
	}
	
	/* (non-Javadoc)
	 * @see converter.HandlingXmlInterface#getListElement(java.lang.String, java.lang.String)
	 */
	public NodeList getListElement(String file,String elmentName) throws IOException, ParserConfigurationException, SAXException
	{
	   DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
	   DocumentBuilder builder= factory.newDocumentBuilder();
	   Document doc= builder.parse(file);
	   return doc.getElementsByTagName(elmentName);
	}
	
	/* (non-Javadoc)
	 * @see converter.HandlingXmlInterface#addCurrency(int, org.w3c.dom.NodeList)
	 */
	public void addCurrency(int i,NodeList curNodeList)
	
	{
		currencyList.add( new Currencie(getInfoCur(i,1,curNodeList),getInfoCur(i,3,curNodeList),
			  getInfoCur(i,5,curNodeList),getInfoCur(i,7,curNodeList),getInfoCur(i,9,curNodeList),
			  getInfoCur(i,11,curNodeList)));
		
	}
	
	/* (non-Javadoc)
	 * @see converter.HandlingXmlInterface#getInfoCur(int, int, org.w3c.dom.NodeList)
	 */
	public String getInfoCur(int currency,int label,NodeList curNodeList)
	{
		return   "   "+curNodeList.item(currency).getChildNodes().item(label)
					 .getTextContent();
	}
	
	/* (non-Javadoc)
	 * @see converter.HandlingXmlInterface#saveUrl()
	 */
	public void saveUrl()throws MalformedURLException, IOException 
	{
		if(indexXml>2)
			return;
		BufferedInputStream in = null;
		FileOutputStream fout = null;
	    try 
	    {	File file = new File("xmlFile/currencies.xml");
	    	in = new BufferedInputStream(new URL(xml[indexXml]).openStream());
	        final byte data[] = new byte[1024];
	    	// if file doesn't exists, then create it
	        if (!file.exists()) 
	        {
	            file.createNewFile();
	        }
	    	fout = new FileOutputStream(file);
	    	
	    	//Read 1024 Characters count book mark the last place we called him
	    	int count;
	        while ((count = in.read(data, 0, 1024)) != -1)
	        {
	            fout.write(data, 0, count);
	        }
	        
	    }finally
	    {
	        if (in != null) 
	            in.close();
	        
	        if (fout != null)
	            fout.close();
	        
	    }
	}
	/* (non-Javadoc)
	 * @see converter.HandlingXmlInterface#saveUrl()
	 */
	public void saveRateCurrency()throws  IOException 
	{
		if(indexXml>2)
			return;
		FileOutputStream fout = null;
	    try 
	    {	File file = new File("curRate/curRate.txt");
	    	
	        
	    	// if file doesn't exists, then create it
	        if (!file.exists()) 
	        {
	            file.createNewFile();
	        }
	    	fout = new FileOutputStream(file);
	    	
	    	FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			for(int i=0;i<currencyList.size();i++)
				bw.write(currencyList.get(i).getName()+"-"+currencyList.get(i).getRate()+"\n");
			bw.close();
	        
	    }finally
	    {
	      
	        
	        if (fout != null)
	            fout.close();
	        
	    }
	}

	/* (non-Javadoc)
	 * @see converter.HandlingXmlInterface#getXmlFileName()
	 */
	public String[] getXmlFileName() 
	{
		return xml;
	}

	/* (non-Javadoc)
	 * @see converter.HandlingXmlInterface#setXml(java.lang.String[])
	 */
	public void setXml(String[] xml) 
	{
		this.xml = xml;
	}

	/* (non-Javadoc)
	 * @see converter.HandlingXmlInterface#isUpdate()
	 */
	public boolean isUpdate()
	{
		return isUpdate;
	}

	/* (non-Javadoc)
	 * @see converter.HandlingXmlInterface#getLastUpdate()
	 */
	public String getLastUpdate()
	{
		return lastUpdate;
	}



}
