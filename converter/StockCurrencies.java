package converter;

import java.util.ArrayList;


public class StockCurrencies implements StockCorrenciesInterface 
{	
	static boolean isUpdate;
	static String lastUpdate;
	static ArrayList<Currencie>  currencyList;
	private static StockCorrenciesInterface stockCurrencies = new StockCurrencies();
	
	private StockCurrencies()
	{	
		HandlingXmlInterface xml=new HandlingXML();
		currencyList=xml.getCurrencyList();
		lastUpdate=xml.getLastUpdate();
		isUpdate=xml.isUpdate();
	}
	
	public static StockCorrenciesInterface getInstanse()
	{
		return stockCurrencies;
	}
	
	/* (non-Javadoc)
	 * @see converter.StockCorrenciesInterface#convertMoney(java.lang.String, java.lang.String, double)
	 */
	public  String convertMoney(String curFrom,String curTo,double amount)
	{
        //No need to convert 
        if(amount==0)
            return "0";
        if(curFrom.matches(curTo)) 
            return Double.toString(amount);
        
        //Else convert money from file
		return convert(curFrom,curTo,amount); 	
	}
	
	
    /* (non-Javadoc)
	 * @see converter.StockCorrenciesInterface#convert(java.lang.String, java.lang.String, double)
	 */
   public String convert(String curFrom,String curTo,double amount) 
   {
       double rateFR=0;   
       double rateTO=0;
       if (curFrom.matches("ILS"))
           rateFR=1;
       if (curTo.matches("ILS"))
           rateTO=1;
       
       //Handling rate from and to in the same loop if find two rate go out
       for(int i= 0;i < currencyList.size()||!(rateTO>0&&rateFR>0);i++)
       {
    	   String curCode=currencyList.get(i).getCurCode().replaceAll("\\s","");
    	  
    	   //Calculate the rate (rate/unit)
           if (rateFR==0&&curFrom.matches(curCode))
               rateFR = currencyList.get(i).convertCurrency();
           if (rateTO==0&&curTo.matches(curCode))
              rateTO = currencyList.get(i).convertCurrency();
       }	
     //Calculate the convert rateFR*amount/rateTO
       return String.format("%.4f", rateFR*amount/rateTO);
    
   }
   
   /* (non-Javadoc)
 * @see converter.StockCorrenciesInterface#getLastUpdate()
 */
public String getLastUpdate()
   {
	 return lastUpdate;
   }
   
   /* (non-Javadoc)
 * @see converter.StockCorrenciesInterface#getCurrencyTable()
 */
public String[][] getCurrencyTable()
   {   
	   if(!isUpdate())
    	   return new String[1][6];
      
	   String currencyInfo[][]=new String[currencyList.size()][6];
	   //Handling rate from and to in the same loop if find two rate go out
       for(int i= 0;i < currencyList.size();i++)   
    		   currencyInfo[i]=currencyList.get(i).getStringInfo();
	   return currencyInfo;
   }
   
	/* (non-Javadoc)
	 * @see converter.StockCorrenciesInterface#isUpdate()
	 */
	public boolean isUpdate()
	{
		return isUpdate;
	}

	/* (non-Javadoc)
	 * @see converter.StockCorrenciesInterface#getCurrencyNodeList()
	 */
	public ArrayList<Currencie>  getCurrencyNodeList() 
	{
		return currencyList;
	}

	/* (non-Javadoc)
	 * @see converter.StockCorrenciesInterface#setCurrency(java.util.ArrayList)
	 */
	public void setCurrency(ArrayList<Currencie> currencyList)
	{
		StockCurrencies.currencyList = currencyList;
	}

	

	/* (non-Javadoc)
	 * @see converter.StockCorrenciesInterface#addNewCurrency(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public boolean addNewCurrency(String name, String curCode, String unit, String country, String change, String  rate)
	{
		
		if(Currencie.checkValidationOfVariables(unit, rate, curCode, country, name, change))
		{
			currencyList.add(new Currencie(name,unit, curCode,country , rate, change));
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see converter.StockCorrenciesInterface#size()
	 */
	public int size() {
		
		return currencyList.size();
	}

	/* (non-Javadoc)
	 * @see converter.StockCorrenciesInterface#getCurrency(int)
	 */
	public Currencie getCurrency(int i) 
	{
		 
		return currencyList.get(i);
	}

	/* (non-Javadoc)
	 * @see converter.StockCorrenciesInterface#getCurrenciesList()
	 */
	public String[] getCurrenciesList() 
	{
		if(size()==0)
			return new String [] {"Empty"};
		int size=size();
		String[] strings=new String[size];
		
		
		for(int i=0;i<size;i++)
			strings[i]=currencyList.get(i).currencyText();
	
		return strings;
	}

	
	/* (non-Javadoc)
	 * @see converter.StockCorrenciesInterface#RemoveAt(int)
	 */
	public void RemoveAt(int selectedRow)
	{
		//Remove from list
		currencyList.remove(selectedRow);
	}

	/* (non-Javadoc)
	 * @see converter.StockCorrenciesInterface#refrechList()
	 */
	public void refrechList() 
	{
		// refreshList
		HandlingXmlInterface xml=new HandlingXML();
		currencyList=xml.getCurrencyList();
		lastUpdate=xml.getLastUpdate();
		isUpdate=xml.isUpdate();
	}
	

}

